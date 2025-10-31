package com.shadowedhunter.entity;

import com.shadowedhunter.util.Direction;

public class Player extends Entity {

    public Player(int x, int y) {
        super(x, y);
    }

    public void move(Direction direction) {
        int newX = position.getX() + direction.getDx();
        int newY = position.getY() + direction.getDy();
        setPosition(newX, newY);
    }

    public void moveNorth() {
        setPosition(position.getX(), position.getY() - 1);
    }

    public void moveSouth() {
        setPosition(position.getX(), position.getY() + 1);
    }

    public void moveEast() {
        setPosition(position.getX() + 1, position.getY());
    }

    public void moveWest() {
        setPosition(position.getX() - 1, position.getY());
    }
}
