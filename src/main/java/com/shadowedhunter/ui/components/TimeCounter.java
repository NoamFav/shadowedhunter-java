package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/** Current floor, elapsed time and death count. */
public class TimeCounter extends VBox {
    private final GameEngine engine;
    private final Label floor = new Label();
    private final Label time = new Label();
    private final Label deaths = new Label();

    public TimeCounter(GameEngine engine) {
        this.engine = engine;
        getStyleClass().addAll("panel", "stats");
        getChildren().addAll(row("Floor", floor), row("Time", time), row("Deaths", deaths));
        update();
    }

    private static BorderPane row(String name, Label value) {
        Label label = new Label(name);
        label.getStyleClass().add("stat-name");
        value.getStyleClass().add("stat-value");
        BorderPane row = new BorderPane();
        row.setLeft(label);
        row.setRight(value);
        return row;
    }

    public void update() {
        var stats = engine.getGameState().getStats();
        floor.setText(String.valueOf(engine.getWorld().getCurrentFloorIndex() + 1));
        time.setText(stats.getFormattedTime());
        deaths.setText(String.valueOf(stats.getDeathCount()));
    }
}
