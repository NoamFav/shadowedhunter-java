package com.shadowedhunter.input.commands.interaction;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.inventory.ItemType;
import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.tiles.ItemTile;

public class PickupCommand implements Command {

    public PickupCommand(GameEngine engine) {}

    @Override
    public void execute(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        var inventory = engine.getGameState().getInventory();
        var floor = engine.getWorld().getCurrentFloor();

        Tile tile = floor.getTile(player.getX(), player.getY());

        if (tile instanceof ItemTile itemTile) {
            String itemCode = itemTile.getItemCode();
            ItemType itemType = ItemType.fromCode(itemCode);

            if (itemType != null) {
                inventory.addItem(itemType);
                itemTile.pickUp();

                String message =
                        switch (itemType) {
                            case KEY ->
                                    "You picked up an old rusty key, it can be used to open locked"
                                            + " door.";
                            case HEALTH_POTION ->
                                    "You picked up a potion of Health, when used it will restore"
                                            + " all your life, use wisely.";
                            default -> "You picked up an item.";
                        };

                engine.displayMessage(message);
                engine.refreshDisplay();
            }
        } else {
            engine.displayMessage("Nothing to pick up");
        }
    }
}
