package com.shadowedhunter.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class World {
    private static final Logger logger = LoggerFactory.getLogger(World.class);
    private final Floor[] floors;
    private int currentFloorIndex;

    public World() {
        this.floors = new Floor[4];
        loadAllFloors();
        this.currentFloorIndex = 0;
    }

    private void loadAllFloors() {
        for (int i = 0; i < 4; i++) {
            floors[i] = new Floor(i);
        }
    }

    public Floor getCurrentFloor() {
        return floors[currentFloorIndex];
    }

    public void switchFloor(int newIndex) {
        if (newIndex >= 0 && newIndex < floors.length) {
            currentFloorIndex = newIndex;
        }
    }

    public void ascendFloor() {
        if (currentFloorIndex < floors.length - 1) {
            currentFloorIndex++;
        }
    }

    public void descendFloor() {
        if (currentFloorIndex > 0) {
            currentFloorIndex--;
        }
    }

    public int getCurrentFloorIndex() {
        return currentFloorIndex;
    }

    public Floor[] getAllFloors() {
        return floors;
    }
}
