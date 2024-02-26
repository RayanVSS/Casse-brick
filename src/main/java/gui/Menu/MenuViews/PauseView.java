package gui.Menu.MenuViews;

import config.Game;
import gui.GameView;
import gui.Menu.MenuControllers.PauseController;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class PauseView extends VBox {
    private Stage primaryStage;
    private Button btnReplay;
    private Button btnQuit;
    private Button btnMenu;
    private Button btnResume;
    private AnimationTimer animationTimer;
    private Pane root;

    public PauseView(Stage p, Pane game, AnimationTimer animationTimer) {
        this.primaryStage = p;
        this.animationTimer = animationTimer;
        this.root = game;
        this.setStyle("-fx-background-color: #273654;");
        this.btnMenu = createButton("Menu", 0, 0);
        this.btnReplay = createButton("Rejouer", 0, 0);
        this.btnResume = createButton("Reprendre", 0, 0);
        this.btnQuit = createButton("Quitter", 0, 0);

        this.setOpacity(0.8);

        this.getChildren().addAll(btnReplay, btnQuit, btnMenu, btnResume);
        this.setLayoutX(game.getLayoutX());
        this.setLayoutY(game.getLayoutY());
        this.setPrefHeight(game.getHeight());
        this.setPrefWidth(game.getWidth());
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        new PauseController(p, this);
    }

    // Getters

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Button getBtnReplay() {
        return btnReplay;
    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Button getBtnMenu() {
        return btnMenu;
    }

    public Button getBtnResume() {
        return btnResume;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public Pane getRoot() {
        return root;
    }

    public Button createButton(String text, double rightMargin, double bottomMargin) {
        Button button = new Button(text);
        StackPane.setMargin(button, new Insets(0, rightMargin, bottomMargin, 0));
        button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-font-size: 20; -fx-background-color: #d5bbb1;-fx-text-fill: #1b263b;");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        });
        return button;
    };
}
