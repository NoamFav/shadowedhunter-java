package com.shadowedhunter.entity;

import com.shadowedhunter.util.Position;

public abstract class Entity {
    protected Position position;

    public Entity(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = new Position(x, y);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
}
