package com.shadowedhunter.input.commands.movement;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.util.Direction;

public class JumpCommand implements Command {
    private final GameEngine engine;
    private final Direction direction;

    public JumpCommand(GameEngine engine, String directionStr) {
        this.engine = engine;
        this.direction = Direction.fromString(directionStr);
    }

    @Override
    public void execute(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        int newX = player.getX() + (direction.getDx() * 2);
        int newY = player.getY() + (direction.getDy() * 2);

        var tile = engine.getWorld().getCurrentFloor().getTile(newX, newY);

        if (tile == null || tile.isWalkable()) {
            player.setPosition(newX, newY);
            engine.moveIcon(direction, 2);
            engine.displayMessage("Jumping " + direction.getName());
            engine.refreshDisplay();
        } else {
            engine.displayMessage("You can't jump there");
        }
    }
}
