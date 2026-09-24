package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.util.Constants;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Segmented health bar. Losses leave a fading trail, changes pop up as numbers, and the bar
 * changes colour and pulses as health runs low.
 */
public class HealthBar extends VBox {
    private static final PseudoClass WARNING = PseudoClass.getPseudoClass("warning");
    private static final PseudoClass CRITICAL = PseudoClass.getPseudoClass("critical");
    private static final int SEGMENTS = 10;
    private static final int WARNING_AT = 60;
    private static final int CRITICAL_AT = 25;

    private final GameEngine engine;
    private final Region track = new Region();
    private final Label value = new Label();
    private final Pane popups = new Pane();
    private final Region glow = new Region();

    // Health shown on the bar and on the trail behind it, from 0 to 1
    private final DoubleProperty shownRatio = new SimpleDoubleProperty(1);
    private final DoubleProperty trailRatio = new SimpleDoubleProperty(1);
    private Timeline animation;
    private final FadeTransition pulse;
    private int shownHealth = -1;

    public HealthBar(GameEngine engine) {
        this.engine = engine;
        getStyleClass().addAll("panel", "health-bar");

        Region heart = new Region();
        heart.getStyleClass().add("heart");
        Label title = new Label("Health");
        title.getStyleClass().add("panel-title");
        value.getStyleClass().add("health-value");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox header = new HBox(heart, title, spacer, value);
        header.getStyleClass().add("health-header");
        header.setAlignment(Pos.CENTER_LEFT);

        track.getStyleClass().add("health-track");
        Region trail = clippedLayer("health-trail", trailRatio);
        Region fill = clippedLayer("health-fill", shownRatio);

        // Notches every 10 HP, drawn over the fill
        HBox segments = new HBox();
        segments.getStyleClass().add("health-segments");
        segments.setMouseTransparent(true);
        for (int i = 0; i < SEGMENTS; i++) {
            Region segment = new Region();
            segment.getStyleClass().add("health-segment");
            HBox.setHgrow(segment, Priority.ALWAYS);
            segment.setMaxWidth(Double.MAX_VALUE);
            if (i == SEGMENTS - 1) segment.getStyleClass().add("last");
            segments.getChildren().add(segment);
        }

        glow.getStyleClass().add("health-glow");
        glow.setOpacity(0);
        glow.setMouseTransparent(true);
        pulse = new FadeTransition(Duration.millis(700), glow);
        pulse.setFromValue(0.15);
        pulse.setToValue(0.9);
        pulse.setAutoReverse(true);
        pulse.setCycleCount(FadeTransition.INDEFINITE);

        StackPane bar = new StackPane(glow, track, trail, fill, segments);
        bar.getStyleClass().add("health-bar-stack");

        // Floating "-40" / "+60" numbers rise from the bar; they're not part of the layout
        popups.setMouseTransparent(true);
        popups.setManaged(false);
        StackPane barWithPopups = new StackPane(bar, popups);
        popups.resizeRelocate(0, 0, 0, 0);

        getChildren().addAll(header, barWithPopups);
        update(false);
    }

    private Region clippedLayer(String styleClass, DoubleProperty ratio) {
        Region layer = new Region();
        layer.getStyleClass().add(styleClass);
        Rectangle clip = new Rectangle();
        clip.heightProperty().bind(track.heightProperty());
        clip.widthProperty().bind(track.widthProperty().multiply(ratio));
        layer.setClip(clip);
        return layer;
    }

    /** @param animate slide to the new value with a trail and a popup instead of jumping */
    public void update(boolean animate) {
        int health = engine.getGameState().getHealth();
        double ratio = (double) health / Constants.MAX_HEALTH;
        int change = shownHealth < 0 ? 0 : health - shownHealth;
        shownHealth = health;

        value.setText(String.valueOf(health));
        pseudoClassStateChanged(WARNING, health <= WARNING_AT && health > CRITICAL_AT);
        pseudoClassStateChanged(CRITICAL, health <= CRITICAL_AT);
        if (health <= CRITICAL_AT) {
            pulse.play();
        } else {
            pulse.stop();
            glow.setOpacity(0);
        }

        if (animation != null) animation.stop();
        if (!animate || change == 0) {
            shownRatio.set(ratio);
            trailRatio.set(ratio);
            return;
        }

        if (change < 0) {
            // The bar drops right away; the trail lingers, then drains to catch up
            animation =
                    new Timeline(
                            new KeyFrame(
                                    Duration.millis(180),
                                    new KeyValue(shownRatio, ratio, Interpolator.EASE_OUT)),
                            new KeyFrame(Duration.millis(500), new KeyValue(trailRatio, trailRatio.get())),
                            new KeyFrame(
                                    Duration.millis(1000),
                                    new KeyValue(trailRatio, ratio, Interpolator.EASE_BOTH)));
        } else {
            trailRatio.set(ratio);
            animation =
                    new Timeline(
                            new KeyFrame(
                                    Duration.millis(450),
                                    new KeyValue(shownRatio, ratio, Interpolator.EASE_OUT)));
        }
        animation.play();
        popup(change, ratio);
    }

    private void popup(int change, double ratio) {
        Label number = new Label((change > 0 ? "+" : "") + change);
        number.getStyleClass().addAll("health-popup", change > 0 ? "heal" : "damage");
        popups.getChildren().add(number);
        number.applyCss();
        number.autosize();
        // Starts at the point on the bar where the health now ends
        number.relocate(track.getWidth() * ratio - number.getWidth() / 2, -number.getHeight() * 0.2);

        TranslateTransition rise = new TranslateTransition(Duration.millis(900), number);
        rise.setByY(-number.getHeight() * 1.2);
        FadeTransition fade = new FadeTransition(Duration.millis(900), number);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setInterpolator(Interpolator.EASE_IN);
        ParallelTransition popup = new ParallelTransition(rise, fade);
        popup.setOnFinished(e -> popups.getChildren().remove(number));
        popup.play();
    }
}
