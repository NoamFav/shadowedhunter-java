package com.shadowedhunter.input.commands.interaction;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.util.Direction;
import com.shadowedhunter.world.Tile;

public class LookCommand implements Command {
    private final GameEngine engine;

    public LookCommand(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        var floor = engine.getWorld().getCurrentFloor();

        StringBuilder description = new StringBuilder();
        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        String[] names = {"north", "south", "east", "west"};

        for (int i = 0; i < directions.length; i++) {
            int checkX = player.getX() + directions[i].getDx();
            int checkY = player.getY() + directions[i].getDy();
            Tile tile = floor.getTile(checkX, checkY);

            String tileDesc = (tile != null) ? tile.getDescription() : "There is nothing";
            description.append(tileDesc).append(" in the ").append(names[i]).append(". ");
        }

        // Current position
        Tile currentTile = floor.getTile(player.getX(), player.getY());
        String currentDesc =
                (currentTile != null) ? currentTile.getDescription() : "There is nothing";
        description.append(currentDesc).append(" where you are.");

        engine.displayMessage(description.toString());
    }
}
