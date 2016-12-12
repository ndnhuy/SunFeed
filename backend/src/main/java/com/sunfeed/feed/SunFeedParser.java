package com.sunfeed.feed;

import java.util.List;

public interface SunFeedParser {
    List<SunEntry> parse(String url);
}
