package com.sunfeed.feed;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest(classes=Application.class)
public class FeedServiceTest {
	
	private SunFeedParser parser = mock(RomeFeedParserAdapter.class);
	private TimeProvider timeProvider = mock(TimeProvider.class);
	
	private FeedService feedService;
	
//	@Rule
//	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		feedService = new FeedService(parser, timeProvider);
	}
	
	@Test
	public void testSearch_withTheDescIsNotLimited_shouldSucceed() {
		SunFeed feed = new SunFeed(Arrays.asList(
									new SunEntry("title", "abc", "feedName", new Date())
								));
		
		String url = "http://www.javaworld.com/index.rss";
		given(parser.parse(url)).willReturn(feed);
		given(timeProvider.now()).willReturn(Instant.now());
		
		SunFeed resultFeed = feedService.search(url, 1000);
		then(parser).should(times(1)).parse(url);
		
		resultFeed.getEntries().stream().forEach(entry -> assertEquals("abc", entry.getDescription()));
	}
	
	@Test
	public void testSearch_withTheDescIsLimited_shouldSucceed() {
		SunFeed feed = new SunFeed(Arrays.asList(
									new SunEntry("title", "abcdef", "feedName", new Date())
								));
		
		String url = "http://www.javaworld.com/index.rss";
		given(parser.parse(url)).willReturn(feed);
		given(timeProvider.now()).willReturn(Instant.now());
		
		SunFeed resultFeed = feedService.search(url, 3);
		then(parser).should(times(1)).parse(url);
		
		resultFeed.getEntries().stream().forEach(entry -> assertEquals("abc...", entry.getDescription()));
	}
	
	@Test
	public void testSearch_withSpecificPublishedOffsetDateTime_shouldDisplayExactTheLifeTime() {
		SunFeed originalFeed = new SunFeed(Arrays.asList(
				new SunEntry("title", "abcdef", "feedName", new Date(Date.parse("Fri, 11 Nov 2016 11:00:40 +0000")))
			));
		
		String url = "http://www.javaworld.com/index.rss";
		given(parser.parse(url)).willReturn(originalFeed);
		
		Instant now = OffsetDateTime.of(2016, 11, 15, 10, 15, 50, 0, ZoneOffset.ofHours(5)).toInstant();
		given(timeProvider.now()).willReturn(now);
		
		SunFeed resultFeed = feedService.search(url, 3);
		
		assertEquals("3d", resultFeed.getEntries().get(0).getLifeTime());
	}
}
