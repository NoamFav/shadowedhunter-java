package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class DoorTile extends Tile {
    private final boolean isTrapped;

    public DoorTile(int x, int y) {
        this(x, y, false);
    }

    public DoorTile(int x, int y, boolean isTrapped) {
        super(x, y, isTrapped ? TileType.TRAP_DOOR : TileType.DOOR, isTrapped ? "t" : "d");
        this.isTrapped = isTrapped;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getDescription() {
        return "There is a door";
    }

    public boolean isTrapped() {
        return isTrapped;
    }
}
