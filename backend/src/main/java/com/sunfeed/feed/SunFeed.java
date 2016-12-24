package com.sunfeed.feed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feed")
public class SunFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String url;

    @JsonIgnore
    @Transient
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
