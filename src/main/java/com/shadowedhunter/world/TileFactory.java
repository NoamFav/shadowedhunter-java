package com.shadowedhunter.world;

import com.shadowedhunter.world.tiles.*;
import com.shadowedhunter.world.traps.TrapType;

public class TileFactory {

    public static Tile createTile(String code, int x, int y) {
        if (code == null || code.isEmpty()) {
            return new EmptyTile(x, y);
        }

        return switch (code) {
            case "x" -> new WallTile(x, y);
            case "d" -> new DoorTile(x, y);
            case "l" -> new LockedDoorTile(x, y);
            case "t" -> new DoorTile(x, y, true); // Trap door
            case "s" -> new SecretDoorTile(x, y);
            case "k" -> new ItemTile(x, y, "k");
            case "he" -> new ItemTile(x, y, "he");
            case "stu" -> new StairsTile(x, y, true);
            case "std" -> new StairsTile(x, y, false);
            case "p" -> new TrapTile(x, y, TrapType.PORTCULLIS);
            case "a" -> new EmptyTile(x, y, "a");
            default -> {
                if (TrapType.fromCode(code) != null) {
                    yield new TrapTile(x, y, TrapType.fromCode(code));
                }
                // Check if it's a lore room code (numbers)
                if (code.matches("\\d+")) {
                    yield new LoreTile(x, y, code);
                }
                yield new EmptyTile(x, y, code);
            }
        };
    }
}
