package com.shadowedhunter.ui.components;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.function.BooleanSupplier;

/** In-game menu opened with Esc. */
public class PauseMenu extends StackPane {
    private final Button resume;
    private final Button music;
    private final BooleanSupplier isMuted;

    public PauseMenu(
            Runnable onResume,
            Runnable onSave,
            Runnable onToggleMusic,
            BooleanSupplier isMuted,
            Runnable onMainMenu,
            Runnable onQuit) {
        this.isMuted = isMuted;
        getStyleClass().add("pause-overlay");
        setVisible(false);

        Label title = new Label("Paused");
        title.getStyleClass().add("pause-title");

        resume = button("Resume", onResume);
        Button save = button("Save game", onSave);
        music =
                button(
                        "",
                        () -> {
                            onToggleMusic.run();
                            updateMusicLabel();
                        });
        Button mainMenu = button("Save & main menu", onMainMenu);
        Button quit = button("Save & quit", onQuit);
        updateMusicLabel();

        VBox panel = new VBox(title, resume, save, music, mainMenu, quit);
        panel.getStyleClass().add("pause-panel");
        panel.setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        getChildren().add(panel);
    }

    private static Button button(String text, Runnable action) {
        Button button = new Button(text);
        button.getStyleClass().addAll("menu-button", "pause-button");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> action.run());
        return button;
    }

    private void updateMusicLabel() {
        music.setText("Music: " + (isMuted.getAsBoolean() ? "off" : "on"));
    }

    public void show() {
        setOpacity(0);
        setVisible(true);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(150), this);
        fadeIn.setToValue(1);
        fadeIn.play();
        resume.requestFocus();
    }

    public void hide() {
        setVisible(false);
    }
}
