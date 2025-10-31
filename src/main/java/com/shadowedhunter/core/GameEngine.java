package com.shadowedhunter.core;

import com.shadowedhunter.input.InputHandler;
import com.shadowedhunter.ui.GameWindow;
import com.shadowedhunter.world.World;

public class GameEngine {
    private static GameEngine instance;
    private final GameState gameState;
    private final World world;
    private final InputHandler inputHandler;
    private GameWindow gameWindow;

    private GameEngine() {
        this.gameState = new GameState();
        this.world = new World();
        this.inputHandler = new InputHandler(this);
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void initialize(GameWindow window) {
        this.gameWindow = window;
        gameState.initialize();
    }

    public void processCommand(String commandText) {
        inputHandler.handleInput(commandText);
    }

    public void displayMessage(String message) {
        if (gameWindow != null) {
            gameWindow.setOutputText(message);
        }
    }

    public void refreshDisplay() {
        if (gameWindow != null) {
            gameWindow.repaint();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public World getWorld() {
        return world;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }
}
