package com.sunfeed.feed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeds")
public class FeedController {
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    private FeedService feedService;



    @GetMapping("/search")
    public List<SunEntry> getFeed(@RequestParam("url") String feedUrl, @RequestParam(name="descSize", required=false) Integer descSize) {
        logger.info("Get feed with url: {}", feedUrl);
        //https://www.javacodegeeks.com/feed/
        if (descSize == null || descSize <= 0) {
            return feedService.search(feedUrl, Integer.MAX_VALUE);
        }

        return feedService.search(feedUrl, descSize);
    }
}
