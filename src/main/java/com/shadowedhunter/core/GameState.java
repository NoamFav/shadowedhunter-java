package com.shadowedhunter.core;

import com.shadowedhunter.entity.Player;
import com.shadowedhunter.inventory.Inventory;

public class GameState {
    private Player player;
    private Inventory inventory;
    private GameStats stats;
    private int health;
    private boolean gameStarted;

    public GameState() {
        this.health = 100;
        this.inventory = new Inventory();
        this.stats = new GameStats();
        this.gameStarted = false;
    }

    public void initialize() {
        this.player = new Player(1, 17);
        this.health = 100;
        this.inventory.clear();
        this.gameStarted = true;
        stats.reset();
    }

    public void handleDeath() {
        stats.incrementDeaths();
        health = 100;
        player.setPosition(1, 17);
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(100, health));
    }

    public void damagePlayer(int damage) {
        setHealth(health - damage);
    }

    public void healPlayer(int amount) {
        setHealth(health + amount);
    }

    public boolean isAlive() {
        return health > 0;
    }

    // Getters/Setters
    public Player getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public GameStats getStats() {
        return stats;
    }

    public int getHealth() {
        return health;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
