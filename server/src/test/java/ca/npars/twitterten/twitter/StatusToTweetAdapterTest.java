package ca.npars.twitterten.twitter;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.MediaEntity;
import twitter4j.Status;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(Enclosed.class)
public class StatusToTweetAdapterTest {

    @RunWith(MockitoJUnitRunner.class)
    public static class TestGetMethods {
        @Mock(answer = Answers.RETURNS_DEEP_STUBS)
        private Status statusMock;

        @Test
        public void testGetScreenName() {
            String expected = "the_screenname";
            when(statusMock.getUser().getScreenName()).thenReturn(expected);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(expected, tweet.getScreenName());
        }

        @Test
        public void testGetUserName() {
            String expected = "The Username";
            when(statusMock.getUser().getName()).thenReturn(expected);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(expected, tweet.getUserName());
        }

        @Test
        public void testGetProfileImage() throws MalformedURLException {
            String urlString = "https://theurl.org";
            URL expected = new URL(urlString);
            when(statusMock.getUser().getBiggerProfileImageURLHttps()).thenReturn(urlString);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(expected, tweet.getProfileImage());
        }

        @Test
        public void testGetProfileImageWithMalformedUrl() throws MalformedURLException {
            String urlString = "a malformed url";
            when(statusMock.getUser().getBiggerProfileImageURLHttps()).thenReturn(urlString);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertNull(tweet.getProfileImage());
        }

        @Test
        public void testGetText() {
            String expected = "the tweet text\uD83C\uDFB5";
            when(statusMock.getText()).thenReturn(expected);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(expected, tweet.getText());
        }

        @Test
        public void testGetImagesNoneValid() {
            MediaEntity mediaEntity = mock(MediaEntity.class);
            MediaEntity[] mediaEntities = {mediaEntity};
            when(statusMock.getMediaEntities()).thenReturn(mediaEntities);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(0, tweet.getImages().size());
        }

        @Test
        public void testGetRetweets() {
            int expected = 42;
            when(statusMock.getRetweetCount()).thenReturn(expected);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(expected, tweet.getRetweets());
        }

        @Test
        public void testGetDate() {
            Date expected = new Date(10000);
            when(statusMock.getCreatedAt()).thenReturn(expected);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(expected, tweet.getDate());
        }
    }

    @RunWith(Parameterized.class)
    public static class TestGetImagesMethod {
        @Parameters
        public static Collection<Object[]> data() throws MalformedURLException {
            String urlString = "https://theimageurl.org";
            URL expectedUrl = new URL(urlString);
            String malformedUrl = "a malformed url";

            return Arrays.asList(new Object[][] {
                    { "photo", urlString, List.of(expectedUrl)},
                    { "animated_gif", urlString, List.of(expectedUrl)},
                    { "video", urlString, Collections.emptyList()},
                    { "photo", malformedUrl, Collections.emptyList()},
            });
        }

        @Mock
        private Status statusMock;

        @Parameter(0) public String type;
        @Parameter(1) public String urlString;
        @Parameter(2) public List<URL> expected;

        @Before
        public void setUp() {
            initMocks(this);
        }

        @Test
        public void testGetImages() throws MalformedURLException {
            MediaEntity mediaEntity = mock(MediaEntity.class);
            when(mediaEntity.getType()).thenReturn(type);
            when(mediaEntity.getMediaURLHttps()).thenReturn(urlString);
            MediaEntity[] mediaEntities = {mediaEntity};

            when(statusMock.getMediaEntities()).thenReturn(mediaEntities);

            Tweet tweet = new StatusToTweetAdapter(statusMock);

            assertEquals(expected, tweet.getImages());
        }
    }
}
