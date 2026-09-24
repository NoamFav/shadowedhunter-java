package com.shadowedhunter.ui.components;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/** Full-screen feedback laid over the game: damage and heal flashes, death, floor changes. */
public class ScreenEffects extends StackPane {
    private final Region damageFlash = layer("damage-flash");
    private final Region healFlash = layer("heal-flash");
    private final VBox deathScreen = new VBox();
    private final Label banner = new Label();

    public ScreenEffects() {
        setMouseTransparent(true);
        setPickOnBounds(false);

        Label died = new Label("You died");
        died.getStyleClass().add("death-title");
        Label respawn = new Label("You wake up at the entrance");
        respawn.getStyleClass().add("death-subtitle");
        deathScreen.getChildren().addAll(died, respawn);
        deathScreen.getStyleClass().add("death-screen");
        deathScreen.setOpacity(0);

        banner.getStyleClass().add("floor-banner");
        banner.setOpacity(0);
        StackPane.setAlignment(banner, Pos.TOP_CENTER);

        getChildren().addAll(damageFlash, healFlash, deathScreen, banner);
    }

    private static Region layer(String styleClass) {
        Region region = new Region();
        region.getStyleClass().add(styleClass);
        region.setOpacity(0);
        return region;
    }

    public void damage() {
        flash(damageFlash, 80, 0, 450);
    }

    public void heal() {
        flash(healFlash, 120, 0, 600);
    }

    public void death() {
        flash(deathScreen, 300, 1400, 700);
    }

    public void banner(String text) {
        banner.setText(text);
        flash(banner, 250, 1100, 600);
    }

    private static void flash(Node node, double inMs, double holdMs, double outMs) {
        FadeTransition in = new FadeTransition(Duration.millis(inMs), node);
        in.setToValue(1);
        FadeTransition out = new FadeTransition(Duration.millis(outMs), node);
        out.setToValue(0);
        new SequentialTransition(in, new PauseTransition(Duration.millis(holdMs)), out).play();
    }
}
