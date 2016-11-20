package com.sunfeed.feed;

import java.util.List;

public class SunFeed {
	List<SunEntry> entries;
	
	public SunFeed() {}
	
	public SunFeed(List<SunEntry> entries) {
		super();
		this.entries = entries;
	}

	public List<SunEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<SunEntry> entries) {
		this.entries = entries;
	}
}
