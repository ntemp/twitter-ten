package ca.npars.twitterten.twitter;

import java.net.URL;
import java.util.Date;
import java.util.List;


/**
 * Interface for tweets that may be serialized into JSON. All subclasses
 * must be public for JSON marshalling to function correctly.
 */
public interface Tweet {
    /**
     * @return A twitter users screenname. ie: An account of @exampleuser would return "exampleuser"
     */
    String getScreenName();

    /**
     * @return A twitter users custom username. ie: "Example User"
     */
    String getUserName();

    /**
     * @return URL to the HTTPS address for a twitter users large profile image
     */
    URL getProfileImage();

    /**
     * @return Text content of a tweet
     */
    String getText();

    /**
     * @return URLs to the HTTPS addresses of images within a tweet
     */
    List<URL> getImages();

    /**
     * @return The number of times a tweet has been retweeted
     */
    int getRetweets();

    /**
     * @return The date a tweet was created
     */
    Date getDate();
}
