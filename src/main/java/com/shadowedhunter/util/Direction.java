package com.shadowedhunter.util;

public enum Direction {
    NORTH(0, -1, "north"),
    SOUTH(0, 1, "south"),
    EAST(1, 0, "east"),
    WEST(-1, 0, "west");

    private final int dx;
    private final int dy;
    private final String name;

    Direction(int dx, int dy, String name) {
        this.dx = dx;
        this.dy = dy;
        this.name = name;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public String getName() {
        return name;
    }

    public static Direction fromString(String direction) {
        return switch (direction.toLowerCase()) {
            case "n", "north" -> NORTH;
            case "s", "south" -> SOUTH;
            case "e", "east" -> EAST;
            case "w", "west" -> WEST;
            default -> null;
        };
    }
}
