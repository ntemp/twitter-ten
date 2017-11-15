package ca.npars.twitterten.twitter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterApiTest {

    @Mock
    private Twitter twitterMock;

    @Test
    public void testRequestsTweetCount() throws TwitterException {
        Paging expectedPaging = new Paging();
        expectedPaging.setCount(10);

        TwitterApi twitterApi = new TwitterApi(twitterMock);
        twitterApi.getTweets("");

        verify(twitterMock).getUserTimeline(any(String.class), eq(expectedPaging));
    }

    @Test
    public void testRequestsAccount() throws TwitterException {
        String expectedAccount = "an account";

        TwitterApi twitterApi = new TwitterApi(twitterMock);
        twitterApi.getTweets(expectedAccount);

        verify(twitterMock).getUserTimeline(eq(expectedAccount), any(Paging.class));
    }

    @Test
    public void testDoesNotReturnNull() throws TwitterException {
        TwitterApi twitterApi = new TwitterApi(twitterMock);
        List<Tweet> tweets = twitterApi.getTweets("");

        assertNotNull(tweets);
    }
}
