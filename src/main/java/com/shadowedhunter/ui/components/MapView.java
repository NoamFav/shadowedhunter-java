package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.util.Constants;
import com.shadowedhunter.util.ResourceLoader;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/** The current floor's map, the player and the fog, scaled to fit the available space. */
public class MapView extends Pane {
    private static final double CORNER_RADIUS = 20;
    // How far the player can see, in tiles
    private static final double SIGHT_RADIUS_TILES = 2.25;
    // Player icon size relative to a tile
    private static final double ICON_SCALE = 0.8;
    // Walking animation speed, and a cap so long moves don't drag on
    private static final double MS_PER_TILE = 90;
    private static final double MAX_WALK_MS = 400;

    // The floor images are large, so they're decoded once and shared between game sessions
    private static Image[] floorImages;
    private static Image playerIcon;

    private final GameEngine engine;
    private final FogOfWar fogOfWar = new FogOfWar();

    private final Canvas canvas = new Canvas();
    private final Region frame = new Region();
    private final ImageView cheatSheet;
    private final Label cheatSheetHint = new Label("Press any key to close");
    private final Rectangle clip = new Rectangle();
    private SequentialTransition cheatSheetAnimation;

    // Where the player is drawn, in tiles; animated towards the real position
    private final DoubleProperty shownX = new SimpleDoubleProperty();
    private final DoubleProperty shownY = new SimpleDoubleProperty();
    private int shownFloor = -1;
    private Timeline walk;

    public MapView(GameEngine engine) {
        this.engine = engine;
        loadImages();

        clip.setArcWidth(CORNER_RADIUS);
        clip.setArcHeight(CORNER_RADIUS);
        canvas.setClip(clip);

        frame.getStyleClass().add("map-frame");
        frame.setMouseTransparent(true);

        cheatSheet = new ImageView(ResourceLoader.loadImage("/images/CheatSheet.png"));
        cheatSheet.setPreserveRatio(true);
        cheatSheet.setVisible(false);
        cheatSheet.setOnMouseClicked(e -> hideCheatSheet());
        cheatSheetHint.getStyleClass().add("cheat-sheet-hint");
        cheatSheetHint.setAlignment(Pos.CENTER);
        cheatSheetHint.visibleProperty().bind(cheatSheet.visibleProperty());
        cheatSheetHint.opacityProperty().bind(cheatSheet.opacityProperty());
        cheatSheetHint.setMouseTransparent(true);

        shownX.addListener((obs, old, x) -> redraw());
        shownY.addListener((obs, old, y) -> redraw());

        getChildren().addAll(canvas, frame, cheatSheet, cheatSheetHint);
        setMinSize(0, 0);
    }

    private static synchronized void loadImages() {
        if (floorImages != null) return;
        floorImages = new Image[4];
        for (int i = 0; i < floorImages.length; i++) {
            floorImages[i] = ResourceLoader.loadImage("/maps/playerFloor" + (i + 1) + ".png");
        }
        playerIcon = ResourceLoader.loadImage("/images/icon.png");
    }

    // The map takes whatever space is left over, so it never asks for any of its own;
    // otherwise it would squeeze the text boxes below it down to their minimum size
    @Override
    protected double computePrefWidth(double height) {
        return 0;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 0;
    }

    @Override
    protected void layoutChildren() {
        Image map = floorImages[0];
        double width = getWidth();
        double height = getHeight();
        if (map == null || width <= 0 || height <= 0) return;

        // Fit the map inside the available space, keeping its aspect ratio
        double scale = Math.min(width / map.getWidth(), height / map.getHeight());
        double mapWidth = map.getWidth() * scale;
        double mapHeight = map.getHeight() * scale;
        double mapX = (width - mapWidth) / 2;
        double mapY = (height - mapHeight) / 2;

        canvas.setWidth(mapWidth);
        canvas.setHeight(mapHeight);
        canvas.relocate(mapX, mapY);
        clip.setWidth(mapWidth);
        clip.setHeight(mapHeight);
        frame.resizeRelocate(mapX, mapY, mapWidth, mapHeight);

        cheatSheet.setFitWidth(width);
        cheatSheet.setFitHeight(height);
        double sheetHeight = cheatSheet.getLayoutBounds().getHeight();
        cheatSheet.relocate(
                (width - cheatSheet.getLayoutBounds().getWidth()) / 2, (height - sheetHeight) / 2);
        cheatSheetHint.resizeRelocate(
                0,
                (height + sheetHeight) / 2 - cheatSheetHint.prefHeight(width) - 8,
                width,
                cheatSheetHint.prefHeight(width));

        redraw();
    }

    /**
     * Moves the drawn player to their current position.
     *
     * @param snap jump straight there (death, a new floor) instead of walking
     */
    public void update(boolean snap) {
        var player = engine.getGameState().getPlayer();
        if (player == null) return;
        int floor = engine.getWorld().getCurrentFloorIndex();
        double targetX = player.getX();
        double targetY = player.getY();

        if (walk != null) walk.stop();
        double distance = Math.abs(targetX - shownX.get()) + Math.abs(targetY - shownY.get());
        if (snap || floor != shownFloor || distance == 0) {
            shownFloor = floor;
            shownX.set(targetX);
            shownY.set(targetY);
            redraw();
            return;
        }

        Duration duration = Duration.millis(Math.min(MAX_WALK_MS, distance * MS_PER_TILE));
        walk =
                new Timeline(
                        new KeyFrame(
                                duration,
                                new KeyValue(shownX, targetX, Interpolator.EASE_OUT),
                                new KeyValue(shownY, targetY, Interpolator.EASE_OUT)));
        walk.play();
    }

    public void redraw() {
        int floor = engine.getWorld().getCurrentFloorIndex();
        Image map = floorImages[floor];
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        if (map == null || width <= 0 || height <= 0) return;

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        gc.drawImage(map, 0, 0, width, height);

        if (engine.getGameState().getPlayer() == null) return;

        // The map images are drawn on a fixed pixel grid, so tiles map straight to pixels
        double tileSize = width * Constants.MAP_TILE_PIXELS / map.getWidth();
        double centerX = (shownX.get() + 0.5) * tileSize;
        double centerY = (shownY.get() + 0.5) * tileSize;

        if (playerIcon != null) {
            double iconHeight = tileSize * ICON_SCALE;
            double iconWidth = iconHeight * playerIcon.getWidth() / playerIcon.getHeight();
            gc.drawImage(
                    playerIcon,
                    centerX - iconWidth / 2,
                    centerY - iconHeight / 2,
                    iconWidth,
                    iconHeight);
        }

        fogOfWar.render(gc, width, height, centerX, centerY, tileSize * SIGHT_RADIUS_TILES);
    }

    public boolean isCheatSheetShowing() {
        return cheatSheet.isVisible();
    }

    public void hideCheatSheet() {
        if (!cheatSheet.isVisible()) return;
        if (cheatSheetAnimation != null) cheatSheetAnimation.stop();
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), cheatSheet);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> cheatSheet.setVisible(false));
        cheatSheetAnimation = new SequentialTransition(fadeOut);
        cheatSheetAnimation.play();
    }

    /** Shows the cheat sheet over the map, then fades it away. */
    public void showCheatSheet(Duration duration) {
        if (cheatSheetAnimation != null) {
            cheatSheetAnimation.stop();
        }
        cheatSheet.setOpacity(1);
        cheatSheet.setVisible(true);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(400), cheatSheet);
        fadeOut.setToValue(0);
        cheatSheetAnimation = new SequentialTransition(new PauseTransition(duration), fadeOut);
        cheatSheetAnimation.setOnFinished(e -> cheatSheet.setVisible(false));
        cheatSheetAnimation.play();
    }
}
