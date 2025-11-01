package com.shadowedhunter.persistence;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.inventory.ItemType;
import com.shadowedhunter.world.Floor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SaveSystem {
    private static final Logger logger = LoggerFactory.getLogger(SaveSystem.class);
    private static final String SAVE_DIR = "Save/";

    public static void saveGame(GameEngine engine, String fileName) {
        File saveDir = new File(SAVE_DIR);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_DIR + fileName))) {

            // Save all floor maps
            Floor[] floors = engine.getWorld().getAllFloors();
            for (Floor floor : floors) {
                var tiles = floor.getAllTiles();
                for (var row : tiles) {
                    StringBuilder line = new StringBuilder();
                    for (int i = 0; i < row.length; i++) {
                        line.append(row[i].getCode());
                        if (i < row.length - 1) line.append(";");
                    }
                    writer.write(line.toString());
                    writer.newLine();
                }
                writer.newLine();
            }

            // Save player position
            var player = engine.getGameState().getPlayer();
            writer.write(player.getX() + "," + player.getY());
            writer.newLine();

            // Save health
            writer.write("Health:" + engine.getGameState().getHealth());
            writer.newLine();

            // Save deaths
            writer.write("Lives:" + engine.getGameState().getStats().getDeathCount());
            writer.newLine();

            // Save inventory
            var inventory = engine.getGameState().getInventory();
            writer.write("Keys:" + inventory.getItemCount(ItemType.KEY));
            writer.newLine();
            writer.write("HealthPotions:" + inventory.getItemCount(ItemType.HEALTH_POTION));
            writer.newLine();

            // Save floor index
            writer.write("MapIndex:" + engine.getWorld().getCurrentFloorIndex());
            writer.newLine();

            logger.info("Game saved successfully to {}", fileName);

        } catch (IOException e) {
            logger.error("Error saving game: ", e);
        }
    }

    public static void loadGame(GameEngine engine, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_DIR + fileName))) {

            // Load maps (not implemented yet - would need to reconstruct floors)
            // Skip map lines for now
            String line;
            int emptyLineCount = 0;
            while ((line = reader.readLine()) != null && emptyLineCount < 4) {
                if (line.isEmpty()) emptyLineCount++;
            }

            // Load player position
            if (line != null) {
                String[] coords = line.split(",");
                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);
                engine.getGameState().getPlayer().setPosition(x, y);
            }

            // Load health
            line = reader.readLine();
            if (line != null) {
                int health = Integer.parseInt(line.split(":")[1]);
                engine.getGameState().setHealth(health);
            }

            // Load deaths
            line = reader.readLine();
            if (line != null) {
                int deaths = Integer.parseInt(line.split(":")[1]);
                engine.getGameState().getStats().setDeathCount(deaths);
            }

            // Load keys
            line = reader.readLine();
            if (line != null) {
                int keys = Integer.parseInt(line.split(":")[1]);
                engine.getGameState().getInventory().setItemCount(ItemType.KEY, keys);
            }

            // Load health potions
            line = reader.readLine();
            if (line != null) {
                int potions = Integer.parseInt(line.split(":")[1]);
                engine.getGameState().getInventory().setItemCount(ItemType.HEALTH_POTION, potions);
            }

            // Load floor index
            line = reader.readLine();
            if (line != null) {
                int floorIndex = Integer.parseInt(line.split(":")[1]);
                engine.getWorld().switchFloor(floorIndex);
            }
            engine.syncIconToPlayerPosition();

            logger.info("Game loaded successfully from {}", fileName);

        } catch (IOException | NumberFormatException e) {
            logger.error("Error loading game: ", e);
        }
    }
}
