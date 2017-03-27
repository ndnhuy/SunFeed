package com.sunfeed.feed;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class FeedServiceTest {

    private SunFeedParser parser = mock(RomeFeedParserAdapter.class);
    private TimeProvider timeProvider = mock(TimeProvider.class);
    private FeedRepository feedRepository = mock(FeedRepository.class);

    private FeedService feedService;

    @Before
    public void setup() {
        feedService = new FeedService(feedRepository, parser, timeProvider);
    }

    @Test
    public void testSearch_withTheDescIsNotLimited_shouldSucceed() {
        List<SunEntry> returnedEntries =
                Stream.of(new SunEntry("title", "abc", "feedName", Instant.now())).collect(Collectors.toList());

        String url = "http://www.javaworld.com/index.rss";
        given(parser.parse(url)).willReturn(returnedEntries);
        given(timeProvider.now()).willReturn(Instant.now());

        List<SunEntry> resultEntries = feedService.search(url, 1000);
        then(parser).should(times(1)).parse(url);

        resultEntries.stream().forEach(entry -> assertEquals("abc", entry.getDescription()));
    }

    @Test
    public void testSearch_withTheDescIsLimited_shouldSucceed() {
        List<SunEntry> returnedEntries =
                Stream.of(new SunEntry("title", "abc", "feedName", Instant.now())).collect(Collectors.toList());

        String url = "http://www.javaworld.com/index.rss";
        given(parser.parse(url)).willReturn(returnedEntries);
        given(timeProvider.now()).willReturn(Instant.now());

        List<SunEntry> resultEntries = feedService.search(url, 3);
        then(parser).should(times(1)).parse(url);

        resultEntries.stream().forEach(entry -> assertEquals("abc...", entry.getDescription()));
    }
}
