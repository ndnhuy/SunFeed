package com.sunfeed.feed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedService.class);
	
	private SunFeedParser parser;
	private TimeProvider timeProvider;
	
	@Autowired
	public FeedService(SunFeedParser parser, TimeProvider timeProvider) {
		this.parser = parser;
		this.timeProvider = timeProvider;
	}
		
	public SunFeed search(String feedUrl, int descSize) {
		SunFeed feed = parser.parse(feedUrl);
		for (SunEntry entry : feed.getEntries()) {
			entry.setLifeTime( AmountOfTimeToString.between(entry.getPublishedDate().toInstant(), 
														    timeProvider.now()) );
			if (entry.getDescription().length() <= descSize) {
				continue;
			}
			
			entry.setDescription(entry.getDescription()
									  .substring(0, descSize)
									  .concat("..."));
		}
		return feed;
	}
	
}
