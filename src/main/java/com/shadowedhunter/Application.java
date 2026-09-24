package com.shadowedhunter;

import com.shadowedhunter.ui.ShadowedHunterApp;

/**
 * Entry point. Kept separate from the JavaFX application class so the game also starts from a
 * plain (shaded) jar, where JavaFX isn't on the module path.
 */
public class Application {
    public static void main(String[] args) {
        javafx.application.Application.launch(ShadowedHunterApp.class, args);
    }
}
