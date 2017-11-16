package ca.npars.twitterten;

import ca.npars.twitterten.twitter.Tweet;
import ca.npars.twitterten.twitter.TwitterApi;
import twitter4j.TwitterException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("twitterten-api")
public class TwitterTenResource {
    private static final String DEFAULT_TWITTER_ACCOUNT = "salesforce";

    @Inject
    private TwitterApi twitterApi;

    public TwitterTenResource() {}

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @Path("tweets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> listTweets() {
        try {
            return this.twitterApi.getTweets(DEFAULT_TWITTER_ACCOUNT);
        } catch (TwitterException e) {
            throw new WebApplicationException(e);
        }
    }
}
