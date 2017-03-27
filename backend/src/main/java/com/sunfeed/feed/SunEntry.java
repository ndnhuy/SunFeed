package com.sunfeed.feed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

public class SunEntry {
    private String title;
    private String description;
    private String feedName;

    @JsonIgnore
    private Instant publishedDate;
    private long publishedDateMilli;

    public SunEntry(String title, String description, String feedName, Instant pubDate) {
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

    public Instant getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Instant publishedDate) {
        this.publishedDate = publishedDate;
    }

    public long getPublishedDateMilli() {
        return this.publishedDateMilli;
    }

    public void setPublishedDateMilli(long milli) {
        this.publishedDateMilli = milli;
    }
}
