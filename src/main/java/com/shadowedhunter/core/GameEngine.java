package com.shadowedhunter.core;

import com.shadowedhunter.input.InputHandler;
import com.shadowedhunter.ui.GameWindow;
import com.shadowedhunter.util.Constants;
import com.shadowedhunter.util.Direction;
import com.shadowedhunter.world.World;

import java.awt.Dimension;
import java.awt.Toolkit;

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

    public void moveIcon(Direction dir, int tiles) {
        if (gameWindow != null) {
            gameWindow.moveIcon(dir, tiles);
        }
    }

    public void setIconPosition(int x, int y) {
        if (gameWindow != null) gameWindow.setIconPosition(x, y);
    }

    public void resetIconToStart() {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        if (s.width == Constants.SCREEN_WIDTH_900P && s.height == Constants.SCREEN_HEIGHT_900P) {
            setIconPosition(Constants.ICON_X_900P, Constants.ICON_Y_900P);
        } else {
            setIconPosition(Constants.ICON_X_1080P, Constants.ICON_Y_1080P);
        }
    }

    public void syncIconToPlayerPosition() {
        if (getGameWindow() == null || getGameState() == null || getGameState().getPlayer() == null)
            return;

        resetIconToStart();
        int dxTiles = getGameState().getPlayer().getX() - Constants.START_X;
        int dyTiles = getGameState().getPlayer().getY() - Constants.START_Y;

        if (dxTiles != 0) {
            moveIcon(dxTiles > 0 ? Direction.EAST : Direction.WEST, Math.abs(dxTiles));
        }
        if (dyTiles != 0) {
            moveIcon(dyTiles > 0 ? Direction.SOUTH : Direction.NORTH, Math.abs(dyTiles));
        }

        refreshDisplay();
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
