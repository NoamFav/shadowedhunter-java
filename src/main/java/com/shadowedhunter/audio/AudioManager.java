package com.shadowedhunter.audio;


public class AudioManager {
    private static AudioManager instance;
    private MusicPlayer backgroundMusic;
    private MusicPlayer menuMusic;
    private boolean muted;

    private AudioManager() {}

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void playBackgroundMusic(String path, double volume) {
        stopBackgroundMusic();
        backgroundMusic = new MusicPlayer(path, volume);
        backgroundMusic.setMuted(muted);
        backgroundMusic.play();
    }

    public void playMenuMusic(String path, double volume) {
        stopMenuMusic();
        menuMusic = new MusicPlayer(path, volume);
        menuMusic.setMuted(muted);
        menuMusic.play();
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic = null;
        }
    }

    public void stopMenuMusic() {
        if (menuMusic != null) {
            menuMusic.stop();
            menuMusic = null;
        }
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
        if (backgroundMusic != null) backgroundMusic.setMuted(muted);
        if (menuMusic != null) menuMusic.setMuted(muted);
    }

    public void stopAll() {
        stopBackgroundMusic();
        stopMenuMusic();
    }
}
