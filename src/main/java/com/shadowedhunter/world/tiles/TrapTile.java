package com.shadowedhunter.world.tiles;

import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.TileType;
import com.shadowedhunter.world.traps.TrapType;

public class TrapTile extends Tile {
    private final TrapType trapType;
    private boolean triggered;

    public TrapTile(int x, int y, TrapType trapType) {
        super(x, y, TileType.TRAP, trapType.getCode());
        this.trapType = trapType;
        this.triggered = false;
    }

    @Override
    public boolean isWalkable() {
        return trapType == TrapType.PORTCULLIS || triggered;
    }

    @Override
    public String getDescription() {
        return switch (trapType) {
            case PORTCULLIS -> "There is a portcullis";
            case HOLE -> "There is nothing";
            default -> "There is nothing";
        };
    }

    public TrapType getTrapType() {
        return trapType;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void trigger() {
        this.triggered = true;
        if (trapType != TrapType.PORTCULLIS) {
            this.code = "";
        }
    }
}
