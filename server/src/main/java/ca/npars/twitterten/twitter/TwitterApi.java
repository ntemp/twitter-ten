package ca.npars.twitterten.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApi {
    private static final int TWEET_COUNT = 10;
    private final Twitter twitter;

    public TwitterApi(String consumerKey, String consumerSecret) {
        ConfigurationBuilder cb = new ConfigurationBuilder()
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public ResponseList<Status> getTweets(String account) throws TwitterException {
        Paging paging = new Paging();
        paging.setCount(TWEET_COUNT);
        return twitter.getUserTimeline(account, paging);
    }
}
