package com.shadowedhunter.audio;

import com.shadowedhunter.util.ResourceLoader;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/** Loops a single music track. */
public class MusicPlayer {
    private static final Logger logger = LoggerFactory.getLogger(MusicPlayer.class);

    private MediaPlayer player;

    /**
     * @param path resource path of the track
     * @param volume between 0.0 (muted) and 1.0 (full)
     */
    public MusicPlayer(String path, double volume) {
        URL url = ResourceLoader.getUrl(path);
        if (url == null) {
            return;
        }
        try {
            player = new MediaPlayer(new Media(url.toExternalForm()));
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.setVolume(volume);
            player.setOnError(() -> logger.error("Error playing music: ", player.getError()));
        } catch (Exception e) {
            logger.error("Error loading music: ", e);
        }
    }

    public void play() {
        if (player != null) {
            player.play();
        }
    }

    public void stop() {
        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
        }
    }

    public void setMuted(boolean muted) {
        if (player != null) {
            player.setMute(muted);
        }
    }

    public void setVolume(double volume) {
        if (player != null) {
            player.setVolume(volume);
        }
    }
}
