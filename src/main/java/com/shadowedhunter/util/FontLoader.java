package com.shadowedhunter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;

public class FontLoader {
    private static final Logger logger = LoggerFactory.getLogger(FontLoader.class);
    private static Font customFont;

    public static Font loadCustomFont() {
        if (customFont != null) {
            return customFont;
        }

        try {
            customFont =
                    Font.createFont(
                                    Font.TRUETYPE_FONT,
                                    FontLoader.class.getResourceAsStream("/fonts/Dungeon.TTF"))
                            .deriveFont(40.0f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            return customFont;
        } catch (IOException | FontFormatException e) {
            logger.error("Error loading custom font: ", e);
            return new Font("Arial", Font.PLAIN, 40);
        }
    }

    public static Font getCustomFont(int size) {
        Font base = loadCustomFont();
        return base.deriveFont((float) size);
    }
}
