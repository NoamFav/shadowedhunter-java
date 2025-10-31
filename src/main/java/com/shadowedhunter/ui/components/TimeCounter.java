package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.util.FontLoader;

import java.awt.*;

public class TimeCounter {
    private final GameEngine engine;

    public TimeCounter(GameEngine engine) {
        this.engine = engine;
    }

    public void render(Graphics2D g2d) {
        Font customFont = FontLoader.loadCustomFont();
        int screenWidth = g2d.getClipBounds() != null ? g2d.getClipBounds().width : 1920;
        int screenHeight = g2d.getClipBounds() != null ? g2d.getClipBounds().height : 1080;

        int barWidth = screenWidth / 12;
        int counterX = screenWidth - barWidth - 20;
        int counterY = screenHeight / 15;
        int lifeCounterY = screenHeight / 9;

        float scaleFactor = (float) screenHeight / 1080;
        int scaleFont = (int) (30 * scaleFactor);

        g2d.setFont(new Font(customFont.getFontName(), customFont.getStyle(), scaleFont));

        // Draw time counter
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(counterX - 10, counterY - 30, barWidth, 40, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(counterX - 10, counterY - 30, barWidth, 40, 20, 20);

        g2d.setColor(Color.WHITE);
        String timeString = "Time: " + engine.getGameState().getStats().getFormattedTime();
        g2d.drawString(timeString, counterX, counterY);

        // Draw life counter
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(counterX - 10, lifeCounterY - 30, barWidth, 40, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(counterX - 10, lifeCounterY - 30, barWidth, 40, 20, 20);

        g2d.setColor(Color.WHITE);
        g2d.drawString(
                "Lives: " + engine.getGameState().getStats().getDeathCount(),
                counterX,
                lifeCounterY);
    }
}
