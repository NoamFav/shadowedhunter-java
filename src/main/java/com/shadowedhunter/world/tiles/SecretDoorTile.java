package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;

public class SecretDoorTile extends Tile {

    public SecretDoorTile(int x, int y) {
        super(x, y, TileType.SECRET_DOOR, "s");
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getDescription() {
        return "There is a wall, a strange-looking wall";
    }
}
