package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class LoreTile extends Tile {
    private final String loreId;

    public LoreTile(int x, int y, String loreId) {
        super(x, y, TileType.LORE, loreId);
        this.loreId = loreId;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String getDescription() {
        return "There is nothing";
    }

    public String getLoreId() {
        return loreId;
    }

    public void markAsRead() {
        this.code = "";
    }
}
