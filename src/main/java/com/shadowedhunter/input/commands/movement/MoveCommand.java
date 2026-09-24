package com.shadowedhunter.input.commands.movement;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.core.TileEvents;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.util.Direction;
import com.shadowedhunter.world.Tile;

public class MoveCommand implements Command {
    private final Direction direction;

    public MoveCommand(GameEngine engine, String directionStr) {
        this.direction = Direction.fromString(directionStr);
    }

    @Override
    public void execute(GameEngine engine) {
        execute(engine, 1);
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
                String obstacle =
                        next == null
                                ? "nothing"
                                : next.getType().toString().toLowerCase().replace("_", " ");
                if (moved == 0) {
                    engine.displayMessage("You can't, there is a " + obstacle + " there");
                } else {
                    engine.displayMessage(
                            "Stopped after "
                                    + moved
                                    + " step"
                                    + (moved == 1 ? "" : "s")
                                    + ": blocked by "
                                    + obstacle
                                    + " ahead");
                }
                break;
            }

            player.move(direction);
            moved++;

            // A trap or a lore room interrupts the walk and keeps its message on screen
            if (TileEvents.onEnter(engine)) {
                break;
            }

            if (moved == steps) {
                engine.displayMessage(
                        "Going " + direction.getName() + (steps > 1 ? " x" + moved : ""));
            }
        }

        engine.refreshDisplay();
    }
}
