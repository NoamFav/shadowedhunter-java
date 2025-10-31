package com.shadowedhunter.ui;

import com.shadowedhunter.audio.AudioManager;
import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.util.Constants;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameWindow extends JFrame {
    private static GameWindow instance;
    private GamePanel gamePanel;
    private final GameEngine engine;

    private GameWindow() {
        this.engine = GameEngine.getInstance();
        engine.initialize(this);

        setTitle("Shadowed Hunter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setUndecorated(true);

        // Set fullscreen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        // Create and add game panel
        gamePanel = new GamePanel(engine);
        setLayout(new BorderLayout());
        add(gamePanel, BorderLayout.CENTER);
        setVisible(true);

        // Start background music
        AudioManager.getInstance().playBackgroundMusic("audio/ThemeMusic.mp3", 20f);

        // Start timer
        int delay = Constants.TIMER_DELAY_MS;
        ActionListener taskPerformer =
                _ -> {
                    engine.getGameState().getStats().incrementTime();
                    gamePanel.repaint();
                };
        new Timer(delay, taskPerformer).start();
    }

    public static GameWindow getInstance() {
        if (instance == null) {
            instance = new GameWindow();
        }
        return instance;
    }

    public void setOutputText(String text) {
        gamePanel.setOutputText(text);
    }

    public void showCheatSheet() {
        gamePanel.showCheatSheet();
    }

    @Override
    public void repaint() {
        super.repaint();
        if (gamePanel != null) {
            gamePanel.repaint();
        }
    }
}
