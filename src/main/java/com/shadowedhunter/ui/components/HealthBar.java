package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.util.FontLoader;

import java.awt.*;

public class HealthBar {
    private final GameEngine engine;

    public HealthBar(GameEngine engine) {
        this.engine = engine;
    }

    public void render(Graphics2D g2d) {
        Font customFont = FontLoader.loadCustomFont();
        int screenWidth = g2d.getClipBounds() != null ? g2d.getClipBounds().width : 1920;
        int screenHeight = g2d.getClipBounds() != null ? g2d.getClipBounds().height : 1080;

        int barWidth = screenWidth / 6;
        int barHeight = screenHeight / 27;

        int containerPadding = (int) (screenWidth * 0.0052);
        int titleHeight = (int) (screenHeight * 0.0389);

        int barX = (int) (screenWidth * 0.0154);
        int barY = (int) (screenHeight * 0.02) + titleHeight + containerPadding;

        float scaleFactor = (float) screenHeight / 1080;
        int scaleFont = (int) (30 * scaleFactor);

        // Draw container
        int containerWidth = barWidth + 2 * containerPadding;
        int containerHeight = barHeight + titleHeight + 2 * containerPadding;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(
                barX - containerPadding,
                barY - titleHeight - containerPadding,
                containerWidth,
                containerHeight,
                20,
                20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(
                barX - containerPadding,
                barY - titleHeight - containerPadding,
                containerWidth,
                containerHeight,
                20,
                20);

        // Draw title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font(customFont.getFontName(), customFont.getStyle(), scaleFont));
        g2d.drawString("Health", barX, barY - containerPadding);

        // Draw health bar background
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(barX, barY, barWidth, barHeight, 20, 20);

        // Draw health bar fill with gradient
        int health = engine.getGameState().getHealth();
        GradientPaint gradient =
                new GradientPaint(barX, barY, Color.RED, barX + barWidth, barY, Color.GREEN, false);
        g2d.setPaint(gradient);

        int healthWidth = (int) ((health / 100.0) * barWidth);
        g2d.fillRoundRect(barX, barY, healthWidth, barHeight, 20, 20);

        // Draw border
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(barX, barY, barWidth, barHeight, 20, 20);
    }
}
