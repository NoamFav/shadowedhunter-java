package com.shadowedhunter.audio;


public class AudioManager {
    private static AudioManager instance;
    private MusicPlayer backgroundMusic;
    private MusicPlayer menuMusic;

    private AudioManager() {}

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void playBackgroundMusic(String path, float volume) {
        stopBackgroundMusic();
        backgroundMusic = new MusicPlayer(path, volume);
        backgroundMusic.play();
    }

    public void playMenuMusic(String path, float volume) {
        stopMenuMusic();
        menuMusic = new MusicPlayer(path, volume);
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

    public void stopAll() {
        stopBackgroundMusic();
        stopMenuMusic();
    }
}
