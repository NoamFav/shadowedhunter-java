package com.shadowedhunter.ui;

import com.shadowedhunter.core.GameEngine;

import java.util.Timer;
import java.util.TimerTask;

public class MessageSystem {
    private static int messageCounter = 0;

    public static void showTimedMessages(GameEngine engine, String[] messages, int delayMs) {
        messageCounter = 0;
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (messageCounter < messages.length) {
                            engine.displayMessage(messages[messageCounter++]);
                        } else {
                            timer.cancel();
                        }
                    }
                },
                0,
                delayMs);
    }
}
