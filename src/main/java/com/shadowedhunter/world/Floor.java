package com.shadowedhunter.world;

import com.shadowedhunter.util.ResourceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Floor {
    private static final Logger logger = LoggerFactory.getLogger(Floor.class);
    private final Tile[][] tiles;
    private final int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.tiles = loadFloorFromFile("maps/floor" + floorNumber + ".csv");
    }

    private Tile[][] loadFloorFromFile(String filePath) {
        List<Tile[]> rows = new ArrayList<>();

        try (InputStream is = ResourceLoader.loadResource(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + filePath);
            }

            String line;
            int y = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Tile[] row = new Tile[values.length];
                for (int x = 0; x < values.length; x++) {
                    row[x] = TileFactory.createTile(values[x], x, y);
                }
                rows.add(row);
                y++;
            }
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Error loading floor file: " + filePath, e);
        }

        return rows.toArray(new Tile[0][]);
    }

    public Tile getTile(int x, int y) {
        if (isValidPosition(x, y)) {
            return tiles[y][x];
        }
        return null;
    }

    public void setTile(int x, int y, Tile tile) {
        if (isValidPosition(x, y)) {
            tiles[y][x] = tile;
        }
    }

    public boolean isValidPosition(int x, int y) {
        return y >= 0 && y < tiles.length && x >= 0 && x < tiles[y].length;
    }

    public int getWidth() {
        return tiles.length > 0 ? tiles[0].length : 0;
    }

    public int getHeight() {
        return tiles.length;
    }

    public Tile[][] getAllTiles() {
        return tiles;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
