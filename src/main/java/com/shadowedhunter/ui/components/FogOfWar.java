// src/main/java/com/shadowedhunter/ui/components/FogOfWar.java
package com.shadowedhunter.ui.components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FogOfWar {
    // Draws fog only over (mapX,mapY,mapW,mapH). playerX/Y are in panel coords.
    public void render(
            Graphics2D g2d, int mapX, int mapY, int mapW, int mapH, int playerX, int playerY) {
        if (mapW <= 0 || mapH <= 0) return;

        // 1) Build an ARGB fog layer the size of the map area
        BufferedImage fog = new BufferedImage(mapW, mapH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D fg = fog.createGraphics();
        fg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 2) Fill it fully black (opaque)
        fg.setComposite(AlphaComposite.SrcOver);
        fg.setColor(new Color(0, 0, 0, 255)); // a bit see-through if you like; 255 for solid
        fg.fillRect(0, 0, mapW, mapH);

        // 3) Punch a soft hole at the player position (relative to map area)
        int cx = playerX - mapX;
        int cy = playerY - mapY;
        float radius = Math.max(mapW, mapH) / 50.0f; // tweak as you like

        // Use DST_OUT so opaque at center removes fog; edge alpha keeps fog
        fg.setComposite(AlphaComposite.DstOut);
        RadialGradientPaint gradient =
                new RadialGradientPaint(
                        cx,
                        cy,
                        radius,
                        new float[] {0f, 1f},
                        new Color[] {new Color(0, 0, 0, 255), new Color(0, 0, 0, 0)});
        fg.setPaint(gradient);
        fg.fillOval(
                (int) (cx - radius), (int) (cy - radius), (int) (radius * 2), (int) (radius * 2));

        fg.dispose();

        // 4) Draw fog layer over the map area
        g2d.drawImage(fog, mapX, mapY, null);
    }
}
