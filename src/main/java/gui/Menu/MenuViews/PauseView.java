package gui.Menu.MenuViews;

import config.StageLevel;
import gui.Menu.MenuControllers.PauseController;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class PauseView extends VBox implements Menu {
    private Stage primaryStage;
    private Button btnReplay;
    private Button btnQuit;
    private Button btnMenu;
    private Button btnResume;
    private AnimationTimer animationTimer;
    private Pane root;
    private StageLevel stageLevel;

    public PauseView(Stage p, Pane game, AnimationTimer animationTimer, StageLevel stageLevel) {
        this.primaryStage = p;
        this.animationTimer = animationTimer;
        this.root = game;
        this.stageLevel = stageLevel;
        //TODO: add css for pink and light
        this.getStylesheets().add(getClass().getResource(GameConstants.CSS).toExternalForm());
        this.getStyleClass().add("pause-view");
        this.btnMenu = createButton("Menu", 0, 0);
        this.btnReplay = createButton("Rejouer", 0, 0);
        this.btnResume = createButton("Reprendre", 0, 0);
        this.btnQuit = createButton("Quitter", 0, 0);

        this.getChildren().addAll(btnReplay, btnQuit, btnMenu, btnResume);
        this.setLayoutX(game.getLayoutX());
        this.setLayoutY(game.getLayoutY());
        this.setPrefHeight(game.getHeight());
        this.setPrefWidth(game.getWidth());
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

    public StageLevel getStageLevel() {
        return stageLevel;
    }

}
