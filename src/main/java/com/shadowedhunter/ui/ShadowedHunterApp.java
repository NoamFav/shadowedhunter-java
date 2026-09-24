package com.shadowedhunter.ui;

import com.shadowedhunter.audio.AudioManager;
import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.persistence.SaveSystem;
import com.shadowedhunter.util.FontLoader;
import com.shadowedhunter.util.ResourceLoader;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Locale;

public class ShadowedHunterApp extends Application {
    // Root font size is the window height divided by this (30px at 1080p); CSS sizes are in em
    private static final double FONT_SIZE_DIVISOR = 36;

    private static final String SAVE_FILE = "Save.txt";

    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        FontLoader.load();

        scene = new Scene(new Group(), 1280, 800, Color.BLACK);
        scene.getStylesheets().add(ResourceLoader.getUrl("/styles/game.css").toExternalForm());
        showMenu();

        // F11 toggles fullscreen; Esc is left alone so it can't drop out of the game by accident
        scene.addEventFilter(
                KeyEvent.KEY_PRESSED,
                e -> {
                    if (e.getCode() == KeyCode.F11) {
                        stage.setFullScreen(!stage.isFullScreen());
                        e.consume();
                    }
                });

        stage.setTitle("Shadowed Hunter");
        Image icon = ResourceLoader.loadImage("/images/AppIcon.png");
        if (icon != null) stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);
        stage.show();
    }

    private void showMenu() {
        MainMenu menu =
                new MainMenu(
                        () -> startGame(false),
                        () -> startGame(true),
                        Platform::exit,
                        SaveSystem.hasSave(SAVE_FILE));
        showScreen(menu.getRoot());
        AudioManager.getInstance().playMenuMusic("/audio/VoidBgMusic.mp3", 0.2);
    }

    // Screens swap the scene's root rather than the scene, so fullscreen is kept
    private void showScreen(Parent root) {
        root.styleProperty()
                .bind(
                        Bindings.format(
                                Locale.ROOT,
                                "-fx-font-size: %.2fpx;",
                                scene.heightProperty().divide(FONT_SIZE_DIVISOR)));
        scene.setRoot(root);

        root.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(350), root);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    private void startGame(boolean fromSave) {
        AudioManager.getInstance().stopMenuMusic();
        GameScreen game = new GameScreen(GameEngine.getInstance(), this::showMenu);
        if (fromSave) {
            SaveSystem.loadGame(GameEngine.getInstance(), SAVE_FILE);
        }
        showScreen(game.getRoot());
        game.start();
    }

    @Override
    public void stop() {
        AudioManager.getInstance().stopAll();
    }
}
