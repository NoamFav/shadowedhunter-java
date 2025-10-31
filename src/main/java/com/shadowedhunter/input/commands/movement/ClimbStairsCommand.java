package com.shadowedhunter.input.commands.movement;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.util.Direction;
import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.tiles.StairsTile;

public class ClimbStairsCommand implements Command {
    private final GameEngine engine;
    private final boolean goingUp;

    public ClimbStairsCommand(GameEngine engine, boolean goingUp) {
        this.engine = engine;
        this.goingUp = goingUp;
    }

    @Override
    public void execute(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        var floor = engine.getWorld().getCurrentFloor();

        // Check all adjacent tiles for stairs
        Direction[] directions = Direction.values();
        for (Direction dir : directions) {
            int checkX = player.getX() + dir.getDx();
            int checkY = player.getY() + dir.getDy();
            Tile tile = floor.getTile(checkX, checkY);

            if (tile instanceof StairsTile stairs && stairs.goesUp() == goingUp) {
                player.setPosition(checkX, checkY);
                if (goingUp) {
                    engine.getWorld().ascendFloor();
                    engine.displayMessage("Going up the stairs");
                } else {
                    engine.getWorld().descendFloor();
                    engine.displayMessage("Going down the stairs");
                }
                engine.refreshDisplay();
                return;
            }
        }

        engine.displayMessage("There is no stairs here");
    }
}
