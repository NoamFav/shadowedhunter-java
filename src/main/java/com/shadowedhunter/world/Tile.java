package com.shadowedhunter.world;

public abstract class Tile {
    protected final int x;
    protected final int y;
    protected TileType type;
    protected String code;

    public Tile(int x, int y, TileType type, String code) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.code = code;
    }

    public abstract boolean isWalkable();

    public abstract String getDescription();

    public TileType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
