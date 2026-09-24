package com.shadowedhunter.core;

import com.shadowedhunter.input.InputHandler;
import com.shadowedhunter.world.World;

import java.util.List;

public class GameEngine {
    private static GameEngine instance;
    private final GameState gameState;
    private final World world;
    private final InputHandler inputHandler;
    private GameView view;

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

    /** Starts a fresh game shown on the given view. */
    public void initialize(GameView view) {
        this.view = view;
        world.reset();
        gameState.initialize();
    }

    public void processCommand(String commandText) {
        inputHandler.handleInput(commandText);
    }

    public void displayMessage(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    public void refreshDisplay() {
        if (view != null) {
            view.refresh();
        }
    }

    public void showCheatSheet() {
        if (view != null) {
            view.showCheatSheet();
        }
    }

    /** Every command phrase the player can type, for completion and suggestions. */
    public List<String> getCommandNames() {
        return inputHandler.getCommandNames();
    }

    public GameState getGameState() {
        return gameState;
    }

    public World getWorld() {
        return world;
    }
}
