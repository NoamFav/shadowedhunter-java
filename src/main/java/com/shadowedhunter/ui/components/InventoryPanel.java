package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.inventory.ItemType;
import com.shadowedhunter.util.FontLoader;
import com.shadowedhunter.util.ResourceLoader;

import java.awt.*;

public class InventoryPanel {
    private final GameEngine engine;
    private final Image keyImage;
    private final Image healthPotionImage;
    private final Image swordImage;
    private final Image shieldImage;

    public InventoryPanel(GameEngine engine) {
        this.engine = engine;

        // Load item images
        keyImage = ResourceLoader.loadImage("/inventoryItems/key.png");
        healthPotionImage = ResourceLoader.loadImage("/inventoryItems/healthPotion.png");
        swordImage = ResourceLoader.loadImage("/inventoryItems/Sword.png");
        shieldImage = ResourceLoader.loadImage("/inventoryItems/Shield.png");
    }

    public void render(Graphics2D g2d) {
        Font customFont = FontLoader.loadCustomFont();
        int screenWidth = g2d.getClipBounds() != null ? g2d.getClipBounds().width : 1920;
        int screenHeight = g2d.getClipBounds() != null ? g2d.getClipBounds().height : 1080;

        int imageWidth = (int) (screenWidth / 17.1);
        int imageHeight = (int) (screenHeight / 9.6);
        int padding = screenWidth / 100;
        int interPadding = screenWidth / 50;
        int titleHeight = screenHeight / 20;
        int barX = (int) (screenWidth * 0.0104);
        int barY = titleHeight + padding + screenHeight / 15;

        // Get inventory counts
        var inventory = engine.getGameState().getInventory();
        int[] itemCounts = {
            inventory.getItemCount(ItemType.KEY),
            inventory.getItemCount(ItemType.HEALTH_POTION),
            1, // Sword (always have)
            1 // Shield (always have)
        };

        int totalWidth = (padding + imageWidth) * 2 + interPadding;
        int totalHeight = (padding + imageHeight) * 2 + interPadding + titleHeight;

        float scaleFactor = (float) screenHeight / 1080;
        int scaleFont = (int) (30 * scaleFactor);
        g2d.setFont(new Font(customFont.getFontName(), Font.PLAIN, scaleFont));

        // Draw container
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(barX, barY, totalWidth, totalHeight, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(barX, barY, totalWidth, totalHeight, 20, 20);

        // Draw title
        g2d.drawString("Inventory:", barX + padding, barY + padding + titleHeight - 35);

        // Draw items
        String[] itemTitles = {"Key", "HP Potion", "Sword", "Shield"};
        Image[] images = {keyImage, healthPotionImage, swordImage, shieldImage};

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                int index = row * 2 + col;
                int itemX = barX + padding + (imageWidth + interPadding) * col;
                int itemY = barY + titleHeight + padding + (imageHeight + interPadding) * row;
                int scaleFont2 = (int) (23 * scaleFactor);

                // Draw item slot
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRoundRect(itemX, itemY, imageWidth, imageHeight, 10, 10);
                g2d.setColor(Color.WHITE);
                g2d.drawRoundRect(itemX, itemY, imageWidth, imageHeight, 10, 10);

                // Draw item title
                g2d.drawString(itemTitles[index], itemX + padding / 2, itemY);

                // Draw item image
                g2d.drawImage(images[index], itemX, itemY, imageWidth, imageHeight, null);

                // Draw count
                g2d.setFont(new Font(customFont.getFontName(), Font.PLAIN, scaleFont2));
                g2d.drawString(
                        String.valueOf(itemCounts[index]),
                        itemX + imageWidth - padding,
                        itemY + imageHeight - padding);
                g2d.setFont(new Font(customFont.getFontName(), Font.PLAIN, scaleFont));
            }
        }
    }
}
