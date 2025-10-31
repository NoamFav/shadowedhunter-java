package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class LockedDoorTile extends Tile {

    public LockedDoorTile(int x, int y) {
        super(x, y, TileType.LOCKED_DOOR, "l");
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getDescription() {
        return "There is a door";
    }

    public void unlock() {
        this.code = "d";
        this.type = TileType.DOOR;
    }
}
