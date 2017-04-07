package com.sunfeed.feed;

public class FeedParsingException extends RuntimeException {
    public FeedParsingException() {}

    public FeedParsingException(String message) {
        super(message);
    }

    public FeedParsingException(Throwable throwable) {
        super(throwable);
    }
}
