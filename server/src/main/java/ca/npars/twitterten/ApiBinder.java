package ca.npars.twitterten;

import ca.npars.twitterten.twitter.TwitterApi;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


/**
 * Binder for injecting an instance of the TwitterApi into the appication.
 */
public class ApiBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // Bind a single instance of the API
        bind(new TwitterApi.Builder().create()).to(TwitterApi.class);
    }
}
