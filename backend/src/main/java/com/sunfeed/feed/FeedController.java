package com.sunfeed.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {
	
	@Autowired
	private FeedService feedService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/me")
	public SunFeed getFeed(@RequestParam("url") String feedUrl, @RequestParam(name="descSize", required=false) Integer descSize) {
		//https://www.javacodegeeks.com/feed/
		if (descSize == null || descSize <= 0) {
			return feedService.search(feedUrl, Integer.MAX_VALUE);
		}
		
        return feedService.search(feedUrl, descSize);
	}
}
