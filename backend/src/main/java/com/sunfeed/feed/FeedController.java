package com.sunfeed.feed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    private FeedService feedService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/me")
    public SunFeed getFeed(@RequestParam("url") String feedUrl, @RequestParam(name="descSize", required=false) Integer descSize) {
        logger.info("Get feed with url: {}", feedUrl);
        //https://www.javacodegeeks.com/feed/
        if (descSize == null || descSize <= 0) {
            return feedService.search(feedUrl, Integer.MAX_VALUE);
        }

        return feedService.search(feedUrl, descSize);
    }
}
