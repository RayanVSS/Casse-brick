package gui.Menu.MenuViews;

import config.Game;
import gui.GameView;
import gui.Menu.MenuControllers.PauseController;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;

public class PauseView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button btnReplay;
    private Button btnQuit;
    private Button btnMenu;
    private Button btnResume;
    private Pane game;
    private AnimationTimer animationTimer;

    public PauseView(Stage p, Pane game, AnimationTimer animationTimer) {
        this.primaryStage = p;
        this.game = game;
        this.animationTimer = animationTimer;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.root.setStyle("-fx-background-color: #273654;");
        this.btnMenu = createButton("Menu", 0, 300);
        this.btnReplay = createButton("Rejouer", 0, 200);
        this.btnResume = createButton("Reprendre", 0, 400);
        this.btnQuit = createButton("Quitter", 0, 100);

        this.root.setOpacity(0.8);

        root.getChildren().addAll(btnReplay, btnQuit, btnMenu, btnResume);
        root.setLayoutX(game.getLayoutX());
        root.setLayoutY(game.getLayoutY());
        root.setPrefHeight(game.getPrefHeight());
        root.setPrefWidth(game.getPrefWidth());
        new PauseController(p, this);
    }

    // Getters
    public StackPane getRoot() {
        return root;
    }

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

    public Pane getGame() {
        return game;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

}
