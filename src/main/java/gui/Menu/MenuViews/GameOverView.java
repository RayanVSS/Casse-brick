package gui.Menu.MenuViews;

import gui.GameView;
import gui.Menu.MenuControllers.GameOverController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Classe GameOver qui représente l'écran de fin de jeu.
 * Cette classe étend VBox pour organiser les éléments de l'interface
 * utilisateur en une colonne verticale.
 * 
 * @see GameOverController
 * @author Benmalek Majda
 */
public class GameOverView extends VBox implements Menu {
    private Stage primaryStage;
    private Button btnReplay;
    private Button btnQuit;
    private Button btnMenu;
    private Label gameOver;
    private GameView gameView;

    /**
     * Constructeur de la classe GameOver.
     * 
     * @param p    Le stage principal de l'application.
     * @param gameView Le pane du jeu.
     */

    public GameOverView(Stage p,GameView gameView ) {
        this.primaryStage = p;
        this.gameView = gameView;
        gameOver = createLabel("Game Over", 0, 0);
        gameOver.getStyleClass().add("title-game-over-style");
        btnReplay=createButton("Rejouer",0,0);
        btnQuit=createButton("Quitter", 0, 0);
        btnMenu = createButton("Menu", 0, 0);
        getChildren().addAll(gameOver, btnReplay, btnMenu, btnQuit);
        getStyleClass().add("root-game-over");
        setLayoutX(gameView.getRoot().getLayoutX());
        setLayoutY(gameView.getRoot().getLayoutY());
        setPrefWidth(gameView.getRoot().getWidth());
        setPrefHeight(gameView.getRoot().getHeight());
        //TODO : ajouter le game over dans le scene manager
        getStylesheets().add(GameConstants.CSS);
        new GameOverController(this);
    }


    /**
     * Getter pour le bouton de replay.
     * 
     * @return Le bouton de replay.
     */
    public Button getBtnReplay() {
        return btnReplay;
    }

    /**
     * Getter pour le bouton de quit.
     * 
     * @return Le bouton de quit.
     */
    public Button getBtnQuit() {
        return btnQuit;
    }

    /**
     * Getter pour le bouton de menu.
     * 
     * @return Le bouton de menu.
     */
    public Button getBtnMenu() {
        return btnMenu;
    }

    /**
     * Getter pour le stage principal.
     * 
     * @return Le stage principal.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Getter pour le label de game over.
     * 
     * @return Le label de game over.
     */
    public Label getGameOver() {
        return gameOver;
    }

    /**
     * Getter pour la VBox racine.
     * 
     * @return La VBox racine.
     */
    public VBox getRoot() {
        return this;
    }

    public GameView getGameView() {
        return gameView;
    }
}
