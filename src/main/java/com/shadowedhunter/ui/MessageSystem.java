package com.shadowedhunter.ui;

import com.shadowedhunter.core.GameEngine;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

/** Plays a sequence of messages (like the intro), one at a time. */
public class MessageSystem {
    private static String[] messages;
    private static int next;
    private static GameEngine engine;
    private static PauseTransition timer;

    /** Shows each message in turn, one every delayMs, replacing any sequence still running. */
    public static void showTimedMessages(GameEngine engine, String[] messages, int delayMs) {
        stop();
        MessageSystem.engine = engine;
        MessageSystem.messages = messages;
        next = 0;
        timer = new PauseTransition(Duration.millis(delayMs));
        timer.setOnFinished(e -> advance());
        advance();
    }

    public static boolean isPlaying() {
        return messages != null;
    }

    /** Shows the next message right away. */
    public static void advance() {
        if (messages == null) return;
        if (next >= messages.length) {
            stop();
            return;
        }
        engine.displayMessage(messages[next++]);
        timer.playFromStart();
    }

    public static void stop() {
        if (timer != null) {
            timer.stop();
        }
        messages = null;
    }
}
