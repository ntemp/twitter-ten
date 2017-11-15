package ca.npars.twitterten.twitter;

import twitter4j.Status;

public class TweetFactory {
    private TweetFactory() {}

    public static Tweet create(Status status) {
        return new StatusToTweetAdapter(status);
    }
}
