package com.shadowedhunter.core;

import com.shadowedhunter.entity.Player;
import com.shadowedhunter.inventory.Inventory;
import com.shadowedhunter.util.Constants;

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
        this.player = new Player(Constants.START_X, Constants.START_Y);
        this.health = Constants.MAX_HEALTH;
        this.inventory.clear();
        this.gameStarted = true;
        stats.reset();

        // sync icon
        GameEngine.getInstance().resetIconToStart();
    }

    public void handleDeath() {
        stats.incrementDeaths();
        health = Constants.MAX_HEALTH;
        player.setPosition(Constants.START_X, Constants.START_Y);
        // The start position only exists on the first floor
        GameEngine.getInstance().getWorld().switchFloor(0);

        // sync icon
        GameEngine.getInstance().resetIconToStart();
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(Constants.MAX_HEALTH, health));
    }

    public void damagePlayer(int damage) {
        setHealth(health - damage);
        if (!isAlive()) {
            handleDeath();
        }
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
