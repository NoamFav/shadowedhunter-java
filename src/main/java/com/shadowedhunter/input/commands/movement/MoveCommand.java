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

        if (tile == null || tile.isWalkable()) {
            player.move(direction);
            engine.moveIcon(direction, 1);
            engine.displayMessage("Going " + direction.getName());
            engine.refreshDisplay();
            checkTileEffects(engine, newX, newY);
        } else {
            engine.displayMessage(
                    "You can't, there is a "
                            + tile.getType().toString().toLowerCase().replace("_", " ")
                            + " there");
        }
    }

    public void execute(GameEngine engine, int steps) {
        if (steps <= 0) steps = 1;

        var player = engine.getGameState().getPlayer();
        var floor = engine.getWorld().getCurrentFloor();

        int moved = 0;

        for (int i = 0; i < steps; i++) {
            int nextX = player.getX() + direction.getDx();
            int nextY = player.getY() + direction.getDy();
            Tile next = floor.getTile(nextX, nextY);

            if (next == null || !next.isWalkable()) {
                if (moved == 0) {
                    engine.displayMessage(
                            "You can't, there is a "
                                    + (next == null
                                            ? "nothing"
                                            : next.getType()
                                                    .toString()
                                                    .toLowerCase()
                                                    .replace("_", " "))
                                    + " there");
                } else {
                    engine.displayMessage(
                            "Stopped after "
                                    + moved
                                    + " step"
                                    + (moved == 1 ? "" : "s")
                                    + ": blocked by "
                                    + (next == null
                                            ? "nothing"
                                            : next.getType()
                                                    .toString()
                                                    .toLowerCase()
                                                    .replace("_", " "))
                                    + " ahead");
                }
                break;
            }

            player.move(direction);
            moved++;
        }

        if (moved > 0) {
            engine.moveIcon(direction, moved);
            engine.refreshDisplay();
            checkTileEffects(engine, player.getX(), player.getY());
            if (moved == steps) {
                engine.displayMessage("Going " + direction.getName() + " x" + moved);
            }
        }
    }

    private void checkTileEffects(GameEngine engine, int x, int y) {
        Tile tile = engine.getWorld().getCurrentFloor().getTile(x, y);
    }
}
