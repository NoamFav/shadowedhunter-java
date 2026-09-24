package com.shadowedhunter.ui.components;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/** Scrolling history of what the game said and what the player typed; newest at the bottom. */
public class MessageLog extends ScrollPane {
    private static final int MAX_ENTRIES = 200;

    private final VBox entries = new VBox();
    private Label latest;

    public MessageLog() {
        getStyleClass().add("message-log");
        entries.getStyleClass().add("log-entries");
        setContent(entries);
        setFitToWidth(true);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setFocusTraversable(false);
        // Follow new entries as they arrive (and as wrapped text settles its height). Deferred,
        // because the skin moves the scroll position back when the content grows.
        entries.heightProperty()
                .addListener((obs, old, height) -> Platform.runLater(() -> setVvalue(getVmax())));
    }

    /** A message from the game. */
    public void addMessage(String text) {
        Label entry = add(text, "log-message");
        if (latest != null) latest.getStyleClass().remove("log-latest");
        entry.getStyleClass().add("log-latest");
        latest = entry;

        FadeTransition fadeIn = new FadeTransition(Duration.millis(220), entry);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    /** A command the player typed, echoed back. */
    public void addCommand(String text) {
        add("› " + text, "log-command");
    }

    private Label add(String text, String styleClass) {
        Label entry = new Label(text);
        entry.setWrapText(true);
        entry.setMaxWidth(Double.MAX_VALUE);
        entry.getStyleClass().addAll("log-entry", styleClass);
        entries.getChildren().add(entry);
        if (entries.getChildren().size() > MAX_ENTRIES) {
            entries.getChildren().remove(0);
        }
        return entry;
    }
}
