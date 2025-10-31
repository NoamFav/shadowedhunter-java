package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class ItemTile extends Tile {
    private final String itemCode;

    public ItemTile(int x, int y, String itemCode) {
        super(x, y, TileType.ITEM, itemCode);
        this.itemCode = itemCode;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public String getDescription() {
        return switch (itemCode) {
            case "k" -> "There is a rusty key";
            case "he" -> "There is a health potion";
            default -> "There is an item";
        };
    }

    public String getItemCode() {
        return itemCode;
    }

    public void pickUp() {
        this.code = "";
        this.type = TileType.EMPTY;
    }
}
