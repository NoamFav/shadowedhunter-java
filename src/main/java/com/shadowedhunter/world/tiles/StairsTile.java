package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class StairsTile extends Tile {
    private final boolean goesUp;

    public StairsTile(int x, int y, boolean goesUp) {
        super(x, y, goesUp ? TileType.STAIRS_UP : TileType.STAIRS_DOWN, goesUp ? "stu" : "std");
        this.goesUp = goesUp;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String getDescription() {
        return goesUp ? "There is a stair going up" : "There is a stair going down";
    }

    public boolean goesUp() {
        return goesUp;
    }
}
