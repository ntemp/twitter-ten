package ca.npars.twitterten.twitter;

import java.net.URL;
import java.util.Date;
import java.util.List;


/**
 * Interface for tweets that may be serialized into JSON. All subclasses
 * must be public for JSON marshalling to function correctly.
 */
public interface Tweet {
    String getScreenName();
    String getUserName();
    URL getProfileImage();
    String getText();
    List<URL> getImages();
    int getRetweets();
    Date getDate();
}
