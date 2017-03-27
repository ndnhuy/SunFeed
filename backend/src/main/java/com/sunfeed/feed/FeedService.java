package com.sunfeed.feed;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedService.class);
    private SunFeedParser parser;
    private TimeProvider timeProvider;
    private FeedRepository feedRepository;

    @Autowired
    public FeedService(FeedRepository repo, SunFeedParser parser, TimeProvider timeProvider) {
        this.parser = parser;
        this.timeProvider = timeProvider;
        this.feedRepository = repo;
    }

    public List<SunFeed> findAll() {
        return feedRepository.findAll();
    }

    public List<SunEntry> search(String feedUrl, int descSize) {
        List<SunEntry> entries = parser.parse(feedUrl);
        for (SunEntry entry : entries) {
            String descText = Jsoup.parse(entry.getDescription()).text();
            if (descSize > descText.length()) {
                continue;
            }
            String briefDescription = descText.
                                            substring(0, descSize).
                                            concat("...");

            entry.setDescription(briefDescription);
        }
        return entries;
    }

}
