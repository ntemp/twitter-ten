package ca.npars.twitterten.twitter;

import twitter4j.Status;

/**
 * Create concrete instance of the Tweet interface from tweet like objects.
 */
public class TweetFactory {
    private TweetFactory() {}

    public static Tweet create(Status status) {
        return new StatusToTweetAdapter(status);
    }
}
