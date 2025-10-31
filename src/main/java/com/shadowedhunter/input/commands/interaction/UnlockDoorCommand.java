package com.shadowedhunter.input.commands.interaction;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.inventory.ItemType;
import com.shadowedhunter.util.Direction;
import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.tiles.LockedDoorTile;

public class UnlockDoorCommand implements Command {
    private final GameEngine engine;
    private final Direction direction;

    public UnlockDoorCommand(GameEngine engine, String directionStr) {
        this.engine = engine;
        this.direction = Direction.fromString(directionStr);
    }

    @Override
    public void execute(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        var inventory = engine.getGameState().getInventory();
        int doorX = player.getX() + direction.getDx();
        int doorY = player.getY() + direction.getDy();

        Tile tile = engine.getWorld().getCurrentFloor().getTile(doorX, doorY);

        if (tile instanceof LockedDoorTile lockedDoor) {
            if (inventory.hasItem(ItemType.KEY)) {
                inventory.removeItem(ItemType.KEY);
                lockedDoor.unlock();
                engine.displayMessage("The " + direction.getName() + " door is now unlocked");
                engine.refreshDisplay();
            } else {
                engine.displayMessage("You don't have a key");
            }
        } else {
            engine.displayMessage("There is no locked door here");
        }
    }
}
