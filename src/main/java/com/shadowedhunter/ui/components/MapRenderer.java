package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.util.Constants;
import com.shadowedhunter.util.ResourceLoader;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MapRenderer {
    private final GameEngine engine;
    private final Image[] floorImages;
    private final Image playerIcon;
    private int iconX;
    private int iconY;

    public MapRenderer(GameEngine engine) {
        this.engine = engine;

        // Load floor images
        floorImages = new Image[4];
        for (int i = 0; i < 4; i++) {
            floorImages[i] = ResourceLoader.loadImage("/maps/playerFloor" + (i + 1) + ".png");
        }

        // Load player icon
        playerIcon = ResourceLoader.loadImage("/icon.png");

        // Initialize icon position based on screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width == Constants.SCREEN_WIDTH_900P
                && screenSize.height == Constants.SCREEN_HEIGHT_900P) {
            iconX = Constants.ICON_X_900P;
            iconY = Constants.ICON_Y_900P;
        } else {
            iconX = Constants.ICON_X_1080P;
            iconY = Constants.ICON_Y_1080P;
        }
    }

    public void render(Graphics2D g2d, int x, int y, int width, int height) {
        int currentFloor = engine.getWorld().getCurrentFloorIndex();
        Image currentMap = floorImages[currentFloor];

        if (currentMap == null) return;

        // Calculate scaling to maintain aspect ratio
        double xScale = (double) width / currentMap.getWidth(null);
        double yScale = (double) height / currentMap.getHeight(null);
        double scale = Math.min(xScale, yScale);

        int scaledWidth = (int) (currentMap.getWidth(null) * scale);
        int scaledHeight = (int) (currentMap.getHeight(null) * scale);

        int mapX = x + (width - scaledWidth) / 2;
        int mapY = y + (height - scaledHeight) / 2;

        // Draw map with rounded clip
        g2d.setClip(new RoundRectangle2D.Float(mapX, mapY, scaledWidth, scaledHeight, 20, 20));
        g2d.drawImage(currentMap, mapX, mapY, scaledWidth, scaledHeight, null);
        g2d.setClip(null);

        // Draw player icon
        int iconWidth = playerIcon.getWidth(null) / Constants.ICON_SIZE_DIVISOR;
        int iconHeight = playerIcon.getHeight(null) / Constants.ICON_SIZE_DIVISOR;
        g2d.drawImage(playerIcon, iconX, iconY, iconWidth, iconHeight, null);

        // Draw border
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(mapX, mapY, scaledWidth, scaledHeight, 20, 20);
    }

    public int getIconX() {
        return iconX;
    }

    public int getIconY() {
        return iconY;
    }

    public void moveIcon(int deltaX, int deltaY) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width == Constants.SCREEN_WIDTH_900P
                && screenSize.height == Constants.SCREEN_HEIGHT_900P) {
            iconX += (int) (deltaX * 0.88);
            iconY += (int) (deltaY * 0.88);
        } else {
            iconX += deltaX;
            iconY += deltaY;
        }
    }

    public void setIconPosition(int x, int y) {
        this.iconX = x;
        this.iconY = y;
    }
}
