package gui.Menu.MenuViews;

import gui.Menu.MenuControllers.GameOverController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;

public class GameOverView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button btnReplay;
    private Button btnQuit;
    private Button btnMenu;
    private Label gameOver;

    public GameOverView(Stage p, Pane game) {
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.gameOver = createLabel("Game Over", 0, 400);
        this.btnMenu = createButton("Menu", 0, 300);
        this.btnReplay = createButton("Rejouer", 0, 200);
        this.btnQuit = createButton("Quitter", 0, 100);
        root.getChildren().addAll(btnReplay, btnQuit, btnMenu, gameOver);
        /*
         * Car le root dans GameView est un pane et non un stackpane, on doit alors
         * adapter manuellement la taille et la position du Pane de gameOver
         */
        this.root.setOpacity(0.9);
        root.setLayoutX(game.getLayoutX());
        root.setLayoutY(game.getLayoutY());
        root.setPrefWidth(game.getWidth());
        root.setPrefHeight(game.getHeight());
        new GameOverController(p, this);
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

    public Scene getScene() {
        return scene;
    }

}
