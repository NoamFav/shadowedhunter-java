package com.shadowedhunter.input.commands.movement;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.util.Direction;
import com.shadowedhunter.world.Tile;

public class MoveCommand implements Command {
    private final GameEngine engine;
    private final Direction direction;

    public MoveCommand(GameEngine engine, String directionStr) {
        this.engine = engine;
        this.direction = Direction.fromString(directionStr);
    }

    @Override
    public void execute(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        int newX = player.getX() + direction.getDx();
        int newY = player.getY() + direction.getDy();

        Tile tile = engine.getWorld().getCurrentFloor().getTile(newX, newY);

        if (tile != null && tile.isWalkable()) {
            player.move(direction);
            engine.displayMessage("Going " + direction.getName());
            engine.refreshDisplay();
            // Check for traps and lore after moving
            checkTileEffects(engine, newX, newY);
        } else if (tile != null) {
            engine.displayMessage(
                    "You can't, there is a "
                            + tile.getType().toString().toLowerCase().replace("_", " ")
                            + " there");
        }
    }

    private void checkTileEffects(GameEngine engine, int x, int y) {
        Tile tile = engine.getWorld().getCurrentFloor().getTile(x, y);
        // Trap and lore checking will be handled by separate systems
    }
}
