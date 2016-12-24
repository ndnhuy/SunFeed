package com.sunfeed.feed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface FeedRepository extends JpaRepository<SunFeed, Long> {
}
