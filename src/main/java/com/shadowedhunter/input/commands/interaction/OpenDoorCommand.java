package com.shadowedhunter.input.commands.interaction;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.util.Direction;
import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.tiles.DoorTile;
import com.shadowedhunter.world.tiles.SecretDoorTile;

public class OpenDoorCommand implements Command {
    private final GameEngine engine;
    private final Direction direction;

    public OpenDoorCommand(GameEngine engine, String directionStr) {
        this.engine = engine;
        this.direction = Direction.fromString(directionStr);
    }

    @Override
    public void execute(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        int doorX = player.getX() + direction.getDx();
        int doorY = player.getY() + direction.getDy();

        // Special check: can't go back at start
        if (doorX == 0 && doorY == 17) {
            engine.displayMessage("You can't turn back, you have a mission to do.");
            return;
        }

        Tile tile = engine.getWorld().getCurrentFloor().getTile(doorX, doorY);

        if (tile instanceof DoorTile door) {
            if (door.isTrapped()) {
                // Handle trapped door
                handleTrappedDoor(engine, doorX, doorY);
            } else {
                engine.displayMessage("Opening the door");
                player.move(direction);
                player.move(direction);
                engine.refreshDisplay();
            }
        } else if (tile instanceof SecretDoorTile) {
            engine.displayMessage(
                    "The wall in front of you feels weird, you try to touch it, "
                            + "the wall moves, it was a secret door!!");
            player.move(direction);
            player.move(direction);
            engine.refreshDisplay();
        } else {
            engine.displayMessage("No door here");
        }
    }

    private void handleTrappedDoor(GameEngine engine, int x, int y) {
        // Trapped door logic
        java.util.Random rand = new java.util.Random();
        double chance = rand.nextDouble();

        if (chance <= 0.50 || engine.getGameState().getHealth() <= 50) {
            engine.displayMessage(
                    "When opening the door, a mechanism triggers, and the door "
                            + "blows up in your face. You died");
            engine.getGameState().handleDeath();
        } else {
            engine.displayMessage(
                    "When opening the door, a mechanism triggers, your reflex "
                            + "saves you, you jump back avoiding the blast. -50HP");
            engine.getGameState().damagePlayer(50);
            // Convert trap door to regular door
            engine.getWorld().getCurrentFloor().setTile(x, y, new DoorTile(x, y, false));
        }
    }
}
