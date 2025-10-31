package com.shadowedhunter.input.commands.inventory;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.inventory.ItemType;
import com.shadowedhunter.util.Constants;

public class UsePotionCommand implements Command {
    private final GameEngine engine;

    public UsePotionCommand(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(GameEngine engine) {
        var inventory = engine.getGameState().getInventory();
        int currentHealth = engine.getGameState().getHealth();

        if (!inventory.hasItem(ItemType.HEALTH_POTION)) {
            engine.displayMessage("You don't have any health potion");
        } else if (currentHealth == Constants.MAX_HEALTH) {
            engine.displayMessage("Your health is already maxed out.");
        } else {
            inventory.removeItem(ItemType.HEALTH_POTION);
            engine.getGameState().setHealth(Constants.MAX_HEALTH);
            engine.displayMessage("Using health potion");
            engine.refreshDisplay();
        }
    }
}
