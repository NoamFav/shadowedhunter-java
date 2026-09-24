package com.shadowedhunter.ui;

import com.shadowedhunter.util.ResourceLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/** Title screen with New Game, Load Game and Close. */
public class MainMenu {
    private final BorderPane root = new BorderPane();

    public MainMenu(Runnable onNewGame, Runnable onLoadGame, Runnable onClose, boolean canLoad) {
        root.getStyleClass().add("main-menu");

        Button close = button("CLOSE", onClose);
        close.getStyleClass().add("small");
        StackPane closeRow = new StackPane(close);
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        closeRow.setPadding(new Insets(10));

        ImageView title = new ImageView(ResourceLoader.loadImage("/images/Title.png"));
        title.setPreserveRatio(true);
        title.fitWidthProperty().bind(root.widthProperty().multiply(0.95));

        VBox top = new VBox(closeRow, title);
        top.setAlignment(Pos.TOP_CENTER);
        root.setTop(top);

        Button newGame = button("NEW GAME", onNewGame);
        Button loadGame = button("LOAD GAME", onLoadGame);
        for (Button b : new Button[] {newGame, loadGame}) {
            b.prefWidthProperty().bind(root.widthProperty().multiply(0.35));
            b.prefHeightProperty().bind(root.heightProperty().multiply(0.7 / 6));
        }

        // Button centres sit at a quarter and three quarters of the width
        HBox buttons = new HBox(newGame, loadGame);
        buttons.setAlignment(Pos.CENTER);
        buttons.spacingProperty().bind(root.widthProperty().multiply(0.15));
        buttons.paddingProperty()
                .bind(
                        root.heightProperty()
                                .map(h -> new Insets(0, 0, h.doubleValue() * 0.1, 0)));
        root.setBottom(buttons);

        loadGame.setDisable(!canLoad);
        newGame.setDefaultButton(true);
    }

    private static Button button(String text, Runnable action) {
        Button button = new Button(text);
        button.getStyleClass().add("menu-button");
        button.setOnAction(e -> action.run());
        return button;
    }

    public Parent getRoot() {
        return root;
    }
}
