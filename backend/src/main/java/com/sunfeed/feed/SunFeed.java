package com.sunfeed.feed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SunFeed {
    private String name;
    private String url;

    @JsonIgnore
    List<SunEntry> entries;

    public SunFeed() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
