package com.shadowedhunter.persistence;

import java.io.Serializable;
import java.util.Map;

public class GameSaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    // Player data
    private int playerX;
    private int playerY;
    private int health;

    // Stats
    private int deathCount;
    private int elapsedSeconds;

    // Inventory
    private Map<String, Integer> inventoryItems;

    // World state
    private int currentFloorIndex;
    private String[][][] allMaps;

    // UI state
    private int iconX;
    private int iconY;

    // Getters and setters
    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public int getElapsedSeconds() {
        return elapsedSeconds;
    }

    public void setElapsedSeconds(int elapsedSeconds) {
        this.elapsedSeconds = elapsedSeconds;
    }

    public Map<String, Integer> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(Map<String, Integer> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public int getCurrentFloorIndex() {
        return currentFloorIndex;
    }

    public void setCurrentFloorIndex(int currentFloorIndex) {
        this.currentFloorIndex = currentFloorIndex;
    }

    public String[][][] getAllMaps() {
        return allMaps;
    }

    public void setAllMaps(String[][][] allMaps) {
        this.allMaps = allMaps;
    }

    public int getIconX() {
        return iconX;
    }

    public void setIconX(int iconX) {
        this.iconX = iconX;
    }

    public int getIconY() {
        return iconY;
    }

    public void setIconY(int iconY) {
        this.iconY = iconY;
    }
}
