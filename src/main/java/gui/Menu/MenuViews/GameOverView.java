package gui.Menu.MenuViews;

import gui.Menu.MenuControllers.GameOverController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe GameOver qui représente l'écran de fin de jeu.
 * Cette classe étend VBox pour organiser les éléments de l'interface
 * utilisateur en une colonne verticale.
 * 
 * @see GameOverController
 * @author Benmalek Majda
 */
public class GameOverView extends VBox {
    private Stage primaryStage;
    private Button btnReplay;
    private Button btnQuit;
    private Button btnMenu;
    private Label gameOver;

    /**
     * Constructeur de la classe GameOver.
     * 
     * @param p    Le stage principal de l'application.
     * @param game Le pane du jeu.
     */

    public GameOverView(Stage p, Pane game) {
        this.primaryStage = p;
        gameOver = new Label("Game Over");
        gameOver.getStyleClass().add("title-game-over-style");
        btnReplay = new Button("Rejouer");
        hoverButton(btnReplay);
        btnQuit = new Button("Quitter");
        hoverButton(btnQuit);
        btnMenu = new Button("Menu");
        hoverButton(btnMenu);
        getChildren().addAll(gameOver, btnReplay, btnMenu, btnQuit);
        getStyleClass().add("root-game-over");
        setLayoutX(game.getLayoutX());
        setLayoutY(game.getLayoutY());
        setPrefWidth(game.getWidth());
        setPrefHeight(game.getHeight());
        new GameOverController(p, this);
    }

    /**
     * Méthode pour gérer l'effet de survol des boutons.
     * 
     * @param button Le bouton sur lequel appliquer l'effet de survol.
     */
    private void hoverButton(Button button) {
        button.getStyleClass().add("button-style");
        button.setOnMouseEntered(e -> {
            button.getStyleClass().remove("button-style");
            button.getStyleClass().add("button-hover");
        });
        button.setOnMouseExited(e -> {
            button.getStyleClass().remove("button-hover");
            button.getStyleClass().add("button-style");
        });
    }

    // getters

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
}
