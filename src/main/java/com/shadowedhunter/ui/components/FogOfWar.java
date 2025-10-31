package com.shadowedhunter.ui.components;

import java.awt.*;

public class FogOfWar {

    public void render(Graphics2D g2d, int width, int height, int playerX, int playerY) {
        // Create fog layer
        g2d.setComposite(AlphaComposite.SrcOver.derive(1f));
        g2d.setColor(new Color(0, 0, 0, 255));
        g2d.fillRect(0, 0, width, height);

        // Create clear area around player
        g2d.setComposite(AlphaComposite.DstOut);
        RadialGradientPaint gradient =
                new RadialGradientPaint(
                        playerX,
                        playerY,
                        (float) width / 30,
                        new float[] {0.0f, 1.0f},
                        new Color[] {new Color(0, 0, 0, 255), new Color(0, 0, 0, 0)});
        g2d.setPaint(gradient);
        g2d.fillOval(
                (int) (playerX - (float) width / 30),
                (int) (playerY - (float) width / 30),
                (int) ((float) width / 30 * 2),
                (int) ((float) width / 30 * 2));

        // Reset composite
        g2d.setComposite(AlphaComposite.SrcOver);
    }
}
