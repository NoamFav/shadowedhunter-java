package com.shadowedhunter.util;

import javafx.scene.image.Image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {
    private static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);

    // Resources live inside this module, so they must be looked up through a class of the
    // module with an absolute path; ClassLoader lookups can't see them on the module path.
    private static String absolute(String path) {
        return path.startsWith("/") ? path : "/" + path;
    }

    public static URL getUrl(String path) {
        URL url = ResourceLoader.class.getResource(absolute(path));
        if (url == null) {
            logger.error("Resource not found: {}", path);
        }
        return url;
    }

    public static Image loadImage(String path) {
        URL url = getUrl(path);
        return url != null ? new Image(url.toExternalForm()) : null;
    }

    public static InputStream loadResource(String path) {
        InputStream is = ResourceLoader.class.getResourceAsStream(absolute(path));
        if (is == null) {
            logger.error("Resource not found: {}", path);
        }
        return is;
    }
}
