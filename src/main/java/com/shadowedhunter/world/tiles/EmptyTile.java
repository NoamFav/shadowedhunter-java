package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class EmptyTile extends Tile {

    public EmptyTile(int x, int y) {
        super(x, y, TileType.EMPTY, "");
    }

    public EmptyTile(int x, int y, String code) {
        super(x, y, TileType.EMPTY, code);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String getDescription() {
        return "There is nothing";
    }
}
