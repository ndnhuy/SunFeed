package com.sunfeed.feed;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/feeds")
public class FeedController {
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    private FeedService feedService;

    @GetMapping("")
    public List<SunFeed> feeds() {
        logger.info("Get all feeds");
        return feedService.findAll();
    }

    @GetMapping("/search")
    public List<SunEntry> getFeed(@RequestParam("url") String feedUrl, @RequestParam(name="descSize", required=false) Integer descSize) {
        logger.info("Get feed with url: {}", feedUrl);

        return feedService.search(feedUrl, descSize == null || descSize <= 0 ? Integer.MAX_VALUE : descSize)
                          .stream()
                          .map(entry -> {
                              SunEntry newEntry = new SunEntry(entry.getTitle(), entry.getDescription(), entry.getFeedName(), entry.getPublishedDate());
                              newEntry.setPublishedDateMilli(entry.getPublishedDate().toEpochMilli());
                              return newEntry;
                          })
                          .collect(toList());

//        if (descSize == null || descSize <= 0) {
//            return feedService.search(feedUrl, Integer.MAX_VALUE);
//        }
//
//        return feedService.search(feedUrl, descSize);
    }
}
