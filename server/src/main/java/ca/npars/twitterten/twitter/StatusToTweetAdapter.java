package ca.npars.twitterten.twitter;

import twitter4j.MediaEntity;
import twitter4j.Status;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Adapts Twitter4j Status objects to our Tweet interface.
 *
 * NOTE: Must be public for Yasson to marshall to JSON.
 */
public class StatusToTweetAdapter implements Tweet {
    private static final Logger LOGGER = Logger.getLogger(StatusToTweetAdapter.class.getName());
    private static final String PHOTO = "photo";
    private static final String GIF = "animated_gif";
    private final Status status;

    StatusToTweetAdapter(Status status) {
        this.status = status;
    }

    @Override
    public String getUserName() {
        return status.getUser().getName();
    }

    @Override
    public String getScreenName() {
        return status.getUser().getScreenName();
    }

    @Override
    public URL getProfileImage() {
        try {
            return new URL(status.getUser().getBiggerProfileImageURLHttps());
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public List<URL> getImages() {
        List<URL> images = new ArrayList<>();
        for (MediaEntity mediaEntity : status.getMediaEntities()) {
            if (!PHOTO.equals(mediaEntity.getType()) && !GIF.equals(mediaEntity.getType())) {
                continue;
            }

            try {
                images.add(new URL(mediaEntity.getMediaURLHttps()));
            } catch (MalformedURLException e) {
                // Ignore malformed URLS
                LOGGER.log(Level.INFO, "Exception while converting MediaURL String to URL object", e);
            }
        }
        return images;
    }

    @Override
    public String getText() {
        return status.getText();
    }

    @Override
    public int getRetweets() {
        return status.getRetweetCount();
    }

    @Override
    public Date getDate() {
        return status.getCreatedAt();
    }
}