package com.shadowedhunter.ui;

import com.shadowedhunter.audio.AudioManager;
import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.core.GameView;
import com.shadowedhunter.persistence.SaveSystem;
import com.shadowedhunter.ui.components.HealthBar;
import com.shadowedhunter.ui.components.InventoryPanel;
import com.shadowedhunter.ui.components.MapView;
import com.shadowedhunter.ui.components.MessageLog;
import com.shadowedhunter.ui.components.PauseMenu;
import com.shadowedhunter.ui.components.ScreenEffects;
import com.shadowedhunter.ui.components.TimeCounter;
import com.shadowedhunter.util.Constants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** The in-game screen: map and HUD on top, message log and command input below. */
public class GameScreen implements GameView {
    private static final String SAVE_FILE = "Save.txt";
    private static final String DEFAULT_HINT =
            "Tab: complete   ·   ↑ ↓: history   ·   help: commands   ·   Esc: menu";

    private final GameEngine engine;
    private final StackPane root;

    private final MapView mapView;
    private final HealthBar healthBar;
    private final InventoryPanel inventoryPanel;
    private final TimeCounter timeCounter;
    private final MessageLog log = new MessageLog();
    private final TextField input = new TextField();
    private final Label hint = new Label(DEFAULT_HINT);
    private final ScreenEffects effects = new ScreenEffects();
    private final PauseMenu pauseMenu;
    private final Timeline clock;

    private final List<String> history = new ArrayList<>();
    private int historyIndex;

    // Last state seen by refresh(), to tell what just happened (damage, death, new floor)
    private boolean started;
    private int lastHealth;
    private int lastDeaths;
    private int lastFloor;

    public GameScreen(GameEngine engine, Runnable onMainMenu) {
        this.engine = engine;

        mapView = new MapView(engine);
        healthBar = new HealthBar(engine);
        inventoryPanel = new InventoryPanel(engine, this::runCommand);
        timeCounter = new TimeCounter(engine);

        VBox leftHud = new VBox(healthBar, inventoryPanel);
        leftHud.getStyleClass().add("hud-column");
        VBox rightHud = new VBox(timeCounter);
        rightHud.getStyleClass().add("hud-column");

        BorderPane top = new BorderPane(mapView);
        top.setLeft(leftHud);
        top.setRight(rightHud);
        VBox.setVgrow(top, Priority.ALWAYS);

        Label prompt = new Label("›");
        prompt.getStyleClass().add("prompt");
        input.getStyleClass().add("command-input");
        input.setPromptText("What do you do?");
        input.setOnAction(e -> submit());
        input.addEventFilter(KeyEvent.KEY_PRESSED, this::onInputKey);
        input.textProperty().addListener((obs, old, text) -> hint.setText(DEFAULT_HINT));
        HBox.setHgrow(input, Priority.ALWAYS);
        HBox inputRow = new HBox(prompt, input);
        inputRow.getStyleClass().add("input-row");
        inputRow.setAlignment(Pos.CENTER_LEFT);

        hint.getStyleClass().add("hint");
        hint.setMaxWidth(Double.MAX_VALUE);

        VBox console = new VBox(log, inputRow, hint);
        console.getStyleClass().add("console");

        VBox game = new VBox(top, console);
        game.getStyleClass().add("game-screen");
        // The console spans most of the width, centred, and shows a few lines of history
        console.maxWidthProperty().bind(game.widthProperty().multiply(0.77));
        log.prefHeightProperty().bind(game.heightProperty().multiply(0.14));
        log.minHeightProperty().bind(game.heightProperty().multiply(0.1));
        game.setAlignment(Pos.TOP_CENTER);

        pauseMenu =
                new PauseMenu(
                        this::resume,
                        () -> {
                            SaveSystem.saveGame(engine, SAVE_FILE);
                            log.addMessage("Game saved");
                        },
                        () -> AudioManager.getInstance().setMuted(!AudioManager.getInstance().isMuted()),
                        () -> AudioManager.getInstance().isMuted(),
                        () -> {
                            SaveSystem.saveGame(engine, SAVE_FILE);
                            stop();
                            onMainMenu.run();
                        },
                        () -> {
                            SaveSystem.saveGame(engine, SAVE_FILE);
                            Platform.exit();
                        });

        root = new StackPane(game, effects, pauseMenu);
        root.addEventFilter(KeyEvent.KEY_PRESSED, this::onKey);
        // Typing should always go to the input, wherever the player clicks
        game.setOnMouseClicked(e -> input.requestFocus());

        clock =
                new Timeline(
                        new KeyFrame(
                                Duration.millis(Constants.TIMER_DELAY_MS),
                                e -> {
                                    engine.getGameState().getStats().incrementTime();
                                    timeCounter.update();
                                }));
        clock.setCycleCount(Timeline.INDEFINITE);

        engine.initialize(this);
        log.addMessage("Type 'intro' to start, or 'help' for the list of commands.");
    }

    public Parent getRoot() {
        return root;
    }

    /** Starts the clock and the music, and hands the keyboard to the command input. */
    public void start() {
        started = true;
        rememberState();
        mapView.update(true);
        refresh();
        clock.play();
        AudioManager.getInstance().playBackgroundMusic("/audio/ThemeMusic.mp3", 0.2);
        Platform.runLater(input::requestFocus);
    }

    private void stop() {
        clock.stop();
        MessageSystem.stop();
        AudioManager.getInstance().stopBackgroundMusic();
    }

    // ---------- Input ----------

    private void submit() {
        String command = input.getText().trim();
        input.clear();

        // The intro can be read at the player's pace: Enter shows the next line,
        // any real command ends it
        if (MessageSystem.isPlaying()) {
            if (command.isEmpty()) {
                MessageSystem.advance();
                return;
            }
            MessageSystem.stop();
        }
        if (command.isEmpty()) return;

        history.add(command);
        historyIndex = history.size();
        runCommand(command);
    }

    private void runCommand(String command) {
        log.addCommand(command);
        engine.processCommand(command);
        refresh();
        input.requestFocus();
    }

    private void onInputKey(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> showHistory(Math.max(0, historyIndex - 1));
            case DOWN -> showHistory(Math.min(history.size(), historyIndex + 1));
            case TAB -> complete();
            default -> {
                return;
            }
        }
        event.consume();
    }

    private void showHistory(int index) {
        historyIndex = index;
        input.setText(historyIndex < history.size() ? history.get(historyIndex) : "");
        input.end();
    }

    // Completes the command being typed as far as it's unambiguous, and lists the options
    private void complete() {
        String typed = input.getText().stripLeading().toLowerCase(Locale.ROOT);
        List<String> matches =
                engine.getCommandNames().stream().filter(name -> name.startsWith(typed)).toList();
        if (matches.isEmpty()) {
            hint.setText("No command starts with '" + typed + "'");
            return;
        }

        String common = matches.get(0);
        for (String match : matches) {
            int i = 0;
            while (i < common.length() && i < match.length() && common.charAt(i) == match.charAt(i)) {
                i++;
            }
            common = common.substring(0, i);
        }
        input.setText(common);
        input.end();
        if (matches.size() > 1) {
            hint.setText(String.join("   ·   ", matches));
        }
    }

    private void onKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            if (pauseMenu.isVisible()) {
                resume();
            } else if (mapView.isCheatSheetShowing()) {
                mapView.hideCheatSheet();
            } else {
                pause();
            }
            event.consume();
        } else if (mapView.isCheatSheetShowing() && !pauseMenu.isVisible()) {
            // Any key closes the cheat sheet, and still reaches the input
            mapView.hideCheatSheet();
        }
    }

    private void pause() {
        clock.pause();
        input.setDisable(true);
        pauseMenu.show();
    }

    private void resume() {
        pauseMenu.hide();
        input.setDisable(false);
        clock.play();
        input.requestFocus();
    }

    // ---------- GameView ----------

    @Override
    public void showMessage(String message) {
        onFxThread(() -> log.addMessage(message));
    }

    @Override
    public void refresh() {
        onFxThread(
                () -> {
                    var state = engine.getGameState();
                    int health = state.getHealth();
                    int deaths = state.getStats().getDeathCount();
                    int floor = engine.getWorld().getCurrentFloorIndex();

                    boolean died = started && deaths > lastDeaths;
                    boolean newFloor = started && floor != lastFloor;
                    if (died) {
                        effects.death();
                    } else if (started && health < lastHealth) {
                        effects.damage();
                    } else if (started && health > lastHealth) {
                        effects.heal();
                    }
                    if (newFloor && !died) {
                        effects.banner("Floor " + (floor + 1));
                    }

                    mapView.update(died || newFloor || !started);
                    healthBar.update(started && !died);
                    inventoryPanel.update();
                    timeCounter.update();
                    rememberState();
                });
    }

    private void rememberState() {
        lastHealth = engine.getGameState().getHealth();
        lastDeaths = engine.getGameState().getStats().getDeathCount();
        lastFloor = engine.getWorld().getCurrentFloorIndex();
    }

    @Override
    public void showCheatSheet() {
        onFxThread(() -> mapView.showCheatSheet(Duration.millis(Constants.CHEAT_SHEET_DISPLAY_MS)));
    }

    private static void onFxThread(Runnable action) {
        if (Platform.isFxApplicationThread()) {
            action.run();
        } else {
            Platform.runLater(action);
        }
    }
}
