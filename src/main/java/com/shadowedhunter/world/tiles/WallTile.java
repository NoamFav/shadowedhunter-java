package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class WallTile extends Tile {

    public WallTile(int x, int y) {
        super(x, y, TileType.WALL, "x");
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getDescription() {
        return "There is a wall";
    }
}
