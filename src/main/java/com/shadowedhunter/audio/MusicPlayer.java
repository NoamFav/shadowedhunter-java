package com.shadowedhunter.audio;

import javazoom.jl.player.Player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.*;

public class MusicPlayer {
    private static final Logger logger = LoggerFactory.getLogger(MusicPlayer.class);
    private final String path;
    private final float volume;
    private Player player;
    private FloatControl volumeControl;
    private volatile boolean keepPlaying = true;
    private Thread playThread;

    public MusicPlayer(String path, float volume) {
        this.path = path;
        this.volume = volume;
    }

    public void play() {
        keepPlaying = true;
        playThread =
                new Thread(
                        () -> {
                            while (keepPlaying) {
                                try {
                                    InputStream is =
                                            getClass().getClassLoader().getResourceAsStream(path);
                                    if (is == null) {
                                        throw new IllegalArgumentException(
                                                "Resource not found: " + path);
                                    }

                                    BufferedInputStream bis = new BufferedInputStream(is);
                                    player = new Player(bis);

                                    // Try to get volume control
                                    trySetupVolumeControl();

                                    player.play();
                                } catch (Exception e) {
                                    logger.error("Error playing music: ", e);
                                }
                            }
                        });
        playThread.start();
    }

    private void trySetupVolumeControl() {
        try {
            Mixer.Info[] mixers = AudioSystem.getMixerInfo();
            for (Mixer.Info mixerInfo : mixers) {
                Mixer mixer = AudioSystem.getMixer(mixerInfo);
                Line.Info[] lineInfos = mixer.getSourceLineInfo();
                for (Line.Info lineInfo : lineInfos) {
                    if (lineInfo.getLineClass().equals(Clip.class)) {
                        try {
                            Clip clip = (Clip) mixer.getLine(lineInfo);
                            if (clip.isControlSupported(FloatControl.Type.VOLUME)) {
                                volumeControl =
                                        (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
                                setVolume(volume);
                                break;
                            }
                        } catch (LineUnavailableException e) {
                            // Continue trying other lines
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("Could not setup volume control", e);
        }
    }

    public void stop() {
        keepPlaying = false;
        if (player != null) {
            player.close();
        }
        if (playThread != null) {
            playThread.interrupt();
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            volumeControl.setValue(volume);
        }
    }
}
