package com.sunfeed.feed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Component
public class RomeFeedParserAdapter implements SunFeedParser {
    private static final Logger logger = LoggerFactory.getLogger(RomeFeedParserAdapter.class);

    private static final String REQUEST_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                                                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                                    "Chrome/54.0.2840.71 Safari/537.36";
    @Override
    public List<SunEntry> parse(String url) {
        try {
            URLConnection conn;
            conn = new URL(url).openConnection();
            // add an 'user-agent' property to the request, otherwise get HTTP 403
            conn.addRequestProperty("user-agent", REQUEST_USER_AGENT);

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(conn));

            List<SunEntry> entries = new ArrayList<SunEntry>();
            for (Object o : feed.getEntries()) {
                SyndEntryImpl entry = (SyndEntryImpl) o;

                SunEntry entryDto = new SunEntry(entry.getTitle(),
                                                 entry.getDescription().getValue(),
                                                 feed.getTitle(),
                                                 entry.getPublishedDate());
                entries.add(entryDto);
            }

            return entries;
        } catch (IllegalArgumentException e) {
            logger.error("Feed type could not be understood by any of the underlying parsers");
            throw new FeedParsingException();
        } catch (FeedException e) {
            logger.error("Feed could not be parsed");
            throw new FeedParsingException();
        } catch (MalformedURLException e) {
            logger.error("Cannot open connection to {}. Illegal protocol or the string could not be parsed", url);
            throw new FeedParsingException(e.getMessage());
        } catch (IOException e) {
            logger.error("Cannot open connection to {}", url);
            throw new FeedParsingException(e.getMessage());
        }
    }

}
