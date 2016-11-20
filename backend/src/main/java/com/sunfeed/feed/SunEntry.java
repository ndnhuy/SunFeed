package com.sunfeed.feed;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SunEntry {
	private String title;
	private String description;
	private String feedName;
	
	@JsonIgnore
	private Date publishedDate;
	
	private String lifeTime;
	
	public SunEntry(String title, String description, String feedName, Date pubDate) {
		super();
		this.title = title;
		this.description = description;
		this.feedName = feedName;
		this.publishedDate = pubDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFeedName() {
		return feedName;
	}
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}
}
