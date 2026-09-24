package com.shadowedhunter.util;

import javafx.scene.text.Font;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    private static final Logger logger = LoggerFactory.getLogger(FontLoader.class);

    /** Family name of the custom font, as referenced by the stylesheet. */
    public static final String FAMILY = "Dungeon";

    private static boolean loaded;

    /** Registers the custom font with JavaFX so the stylesheet can use it. */
    public static void load() {
        if (loaded) {
            return;
        }
        try (InputStream is = ResourceLoader.loadResource("/fonts/Dungeon.TTF")) {
            if (is == null || Font.loadFont(is, 40) == null) {
                logger.error("Custom font could not be loaded, falling back to the default font");
            }
            loaded = true;
        } catch (IOException e) {
            logger.error("Error loading custom font: ", e);
        }
    }
}
