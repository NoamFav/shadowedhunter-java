package com.shadowedhunter.core;

import java.util.concurrent.atomic.AtomicInteger;

public class GameStats {
    private final AtomicInteger elapsedSeconds;
    private int deathCount;

    public GameStats() {
        this.elapsedSeconds = new AtomicInteger(0);
        this.deathCount = 0;
    }

    public void reset() {
        elapsedSeconds.set(0);
        deathCount = 0;
    }

    public void incrementTime() {
        elapsedSeconds.incrementAndGet();
    }

    public void incrementDeaths() {
        deathCount++;
    }

    public int getElapsedSeconds() {
        return elapsedSeconds.get();
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int count) {
        this.deathCount = count;
    }

    public String getFormattedTime() {
        int total = elapsedSeconds.get();
        int hours = total / 3600;
        int minutes = (total % 3600) / 60;
        int seconds = total % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
