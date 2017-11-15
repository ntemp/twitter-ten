package ca.npars.twitterten.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Twitter API wrapper for retrieving tweets from Twitter4j in a format friendly to
 * our app.
 */
public class TwitterApi {
    private static final int TWEET_COUNT = 10;
    private final Twitter twitter;

    TwitterApi(Twitter twitter) {
        this.twitter = twitter;
    }

    public List<Tweet> getTweets(String account) throws TwitterException {
        Paging paging = new Paging();
        paging.setCount(TWEET_COUNT);

        ResponseList<Status> userTimeline = twitter.getUserTimeline(account, paging);
        if (userTimeline == null) {
            return Collections.emptyList();
        }

        return userTimeline.stream()
                .map(TweetFactory::create)
                .collect(toList());
    }

    /**
     * Builder for TwitterApi.
     */
    public static class Builder {
        private static final String TWITTER_CONSUMER_KEY = "TWITTER_CONSUMER_KEY";
        private static final String TWITTER_CONSUMER_SECRET = "TWITTER_CONSUMER_SECRET";
        private static final String TWITTER_ACCESS_TOKEN = "TWITTER_ACCESS_TOKEN";
        private static final String TWITTER_ACCESS_TOKEN_SECRET = "TWITTER_ACCESS_TOKEN_SECRET";

        private String consumerKey;
        private String consumerSecret;
        private String accessToken;
        private String accessTokenSecret;

        public Builder() {
            /* Default to system env vars */
            consumerKey = System.getenv(TWITTER_CONSUMER_KEY);
            consumerSecret = System.getenv(TWITTER_CONSUMER_SECRET);
            accessToken = System.getenv(TWITTER_ACCESS_TOKEN);
            accessTokenSecret = System.getenv(TWITTER_ACCESS_TOKEN_SECRET);
        }

        public Builder consumerKey(String val) { consumerKey = val; return this; }
        public Builder consumerSecret(String val) { consumerSecret = val; return this; }
        public Builder accessToken(String val) { accessToken = val; return this; }
        public Builder accessTokenSecret(String val) { accessTokenSecret = val; return this; }

        public TwitterApi create() {
            /*
             * This isn't really a builder pattern as the create method is doing some work here.
             * Could pull this out into a TwitterApiFactory but that seems excessive for the use
             * case as is.
             */
            ConfigurationBuilder cb = new ConfigurationBuilder()
                    .setOAuthConsumerKey(consumerKey)
                    .setOAuthConsumerSecret(consumerSecret)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthAccessTokenSecret(accessTokenSecret);
            TwitterFactory tf = new TwitterFactory(cb.build());

            return new TwitterApi(tf.getInstance());
        }
    }
}
