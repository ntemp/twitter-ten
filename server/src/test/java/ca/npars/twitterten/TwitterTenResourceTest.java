package ca.npars.twitterten;

import javax.ws.rs.core.Response;

import ca.npars.twitterten.twitter.TwitterApi;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import twitter4j.TwitterException;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TwitterTenResourceTest extends JerseyTest {
    @Mock
    private TwitterApi twitterApi;

    @Override
    public ResourceConfig configure() {
        // Register our resource with a mocked api binding
        MockitoAnnotations.initMocks(this);
        return new ResourceConfig()
                .register(TwitterTenResource.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(twitterApi).to(TwitterApi.class);
                    }
                });
    }

    @Test
    public void testListTweets() throws TwitterException {
        String expectedBody = "[]";

        when(twitterApi.getTweets(any())).thenReturn(Collections.emptyList());

        Response response = target("twitterten-api/tweets").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expectedBody, response.readEntity(String.class));
    }

    @Test
    public void testServerErrorOnException() throws TwitterException {
        when(twitterApi.getTweets(any())).thenThrow(new TwitterException("A test exception"));

        Response response = target("twitterten-api/tweets").request().get();
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
