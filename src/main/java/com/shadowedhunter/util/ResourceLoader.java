package com.shadowedhunter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Image;
import java.io.InputStream;

import javax.swing.ImageIcon;

public class ResourceLoader {
    private static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    public static Image loadImage(String path) {
        try {
            return new ImageIcon(ResourceLoader.class.getResource(path)).getImage();
        } catch (Exception e) {
            logger.error("Failed to load image: " + path, e);
            return null;
        }
    }

    public static InputStream loadResource(String path) {
        try {
            return ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        } catch (Exception e) {
            logger.error("Failed to load resource: " + path, e);
            return null;
        }
    }
}
