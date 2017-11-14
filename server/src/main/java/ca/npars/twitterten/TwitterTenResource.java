package ca.npars.twitterten;

import ca.npars.twitterten.twitter.TwitterApi;
import twitter4j.TwitterException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("twitterten-api")
public class TwitterTenResource {
    private static final String TWITTER_CONSUMER_KEY = "TWITTER_CONSUMER_KEY";
    private static final String TWITTER_CONSUMER_SECRET = "TWITTER_CONSUMER_SECRET";

    private final TwitterApi twitterApi;

    public TwitterTenResource() {
        String consumerKey = System.getenv(TWITTER_CONSUMER_KEY);
        String consumerSecret = System.getenv(TWITTER_CONSUMER_SECRET);
        twitterApi = new TwitterApi(consumerKey, consumerSecret);
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @Path("tweets")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listTweets() {
        return "Got it!";
    }
}
