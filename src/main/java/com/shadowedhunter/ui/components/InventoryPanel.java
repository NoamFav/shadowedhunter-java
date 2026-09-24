package com.shadowedhunter.ui.components;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.inventory.ItemType;
import com.shadowedhunter.util.ResourceLoader;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.function.Consumer;

/** What the player carries: consumables with counts, and their equipment. */
public class InventoryPanel extends VBox {
    private static final PseudoClass EMPTY = PseudoClass.getPseudoClass("empty");
    private static final PseudoClass USABLE = PseudoClass.getPseudoClass("usable");

    private final GameEngine engine;
    private final Slot keys;
    private final Slot potions;

    /** @param runCommand runs a command as if the player typed it (clicking the potion uses it) */
    public InventoryPanel(GameEngine engine, Consumer<String> runCommand) {
        this.engine = engine;
        getStyleClass().addAll("panel", "inventory");

        Label title = new Label("Inventory");
        title.getStyleClass().add("panel-title");

        keys =
                new Slot(
                        "Rusty key",
                        "/images/inventoryItems/key.png",
                        "Opens one locked door, then breaks.\nunlock <direction> -ern door",
                        false);
        potions =
                new Slot(
                        "Health potion",
                        "/images/inventoryItems/healthPotion.png",
                        "Restores all your health.\nClick it, or type: use potion",
                        false);
        potions.setOnMouseClicked(
                e -> {
                    if (engine.getGameState().getInventory().hasItem(ItemType.HEALTH_POTION)) {
                        runCommand.accept("use potion");
                    }
                });
        HBox items = new HBox(keys, potions);
        items.getStyleClass().add("inventory-row");

        Label equippedTitle = new Label("Equipped");
        equippedTitle.getStyleClass().add("inventory-section");
        equippedTitle.setMaxWidth(Double.MAX_VALUE);
        Slot sword =
                new Slot("Sword", "/images/inventoryItems/Sword.png", "Your trusty blade.", true);
        Slot shield =
                new Slot("Shield", "/images/inventoryItems/Shield.png", "Dented, but holding.", true);
        HBox equipped = new HBox(sword, shield);
        equipped.getStyleClass().add("inventory-row");

        getChildren().addAll(title, items, equippedTitle, equipped);
        update();
    }

    public void update() {
        var inventory = engine.getGameState().getInventory();
        keys.setCount(inventory.getItemCount(ItemType.KEY));
        potions.setCount(inventory.getItemCount(ItemType.HEALTH_POTION));
        potions.pseudoClassStateChanged(USABLE, inventory.hasItem(ItemType.HEALTH_POTION));
    }

    /** One item: its picture in a lit frame, a count badge and its name. */
    private static class Slot extends VBox {
        private final StackPane frame;
        private final Label badge = new Label();
        private int count = -1;

        Slot(String name, String imagePath, String description, boolean equipment) {
            getStyleClass().add("inventory-item");
            setAlignment(Pos.TOP_CENTER);

            ImageView image = new ImageView(ResourceLoader.loadImage(imagePath));
            image.setPreserveRatio(true);
            image.setSmooth(true);

            badge.getStyleClass().add("inventory-badge");
            StackPane.setAlignment(badge, Pos.TOP_RIGHT);

            Region shine = new Region();
            shine.getStyleClass().add("inventory-shine");
            shine.setMouseTransparent(true);

            frame = new StackPane(image, shine);
            frame.getStyleClass().add(equipment ? "equipment-slot" : "inventory-slot");
            // The frame has a fixed size (see the stylesheet), so the image can follow it
            image.fitWidthProperty().bind(frame.widthProperty().multiply(0.82));
            if (!equipment) frame.getChildren().add(badge);

            Label label = new Label(name);
            label.getStyleClass().add("inventory-item-name");

            getChildren().addAll(frame, label);

            Tooltip tooltip = new Tooltip(name + "\n" + description);
            tooltip.getStyleClass().add("inventory-tooltip");
            tooltip.setShowDelay(Duration.millis(250));
            Tooltip.install(this, tooltip);
        }

        void setCount(int newCount) {
            boolean gained = count >= 0 && newCount > count;
            count = newCount;
            badge.setText(String.valueOf(newCount));
            badge.setVisible(newCount > 0);
            pseudoClassStateChanged(EMPTY, newCount == 0);
            if (gained) celebrate();
        }

        // A little bounce and flash when something is picked up
        private void celebrate() {
            ScaleTransition up = new ScaleTransition(Duration.millis(130), frame);
            up.setToX(1.15);
            up.setToY(1.15);
            ScaleTransition down = new ScaleTransition(Duration.millis(220), frame);
            down.setToX(1);
            down.setToY(1);
            new SequentialTransition(up, down).play();

            Region flash = new Region();
            flash.getStyleClass().add("inventory-flash");
            flash.setMouseTransparent(true);
            frame.getChildren().add(flash);
            FadeTransition fade = new FadeTransition(Duration.millis(700), flash);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setOnFinished(e -> frame.getChildren().remove(flash));
            fade.play();
        }
    }
}
