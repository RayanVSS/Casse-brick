package gui.Menu.MenuViews;

import gui.Menu.MenuControllers.GameOverController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Classe GameOverView qui implémente l'interface Menu pour représenter la vue
 * de fin de partie.
 * Elle contient les éléments graphiques de la vue de fin de partie.
 * 
 * @author Benmalek Majda
 */
public class GameOverView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private VBox root;
    private Button btnReplay;
    private Button btnQuit;
    private Button btnMenu;
    private Label gameOver;

    /**
     * Constructeur de GameOverView.
     * 
     * @param p    Le stage principal sur lequel la vue de fin de partie est
     *             affichée.
     * @param game Le Pane du GameView.
     */
    public GameOverView(Stage p, Pane game) {
        this.primaryStage = p;
        this.root = new VBox();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.gameOver = createLabel("Game Over", 0, 400, 40);
        this.btnMenu = createButton("Menu", 0, 300);
        this.btnReplay = createButton("Rejouer", 0, 200);
        this.btnQuit = createButton("Quitter", 0, 100);
        root.getChildren().addAll(gameOver, btnReplay, btnMenu, btnQuit);
        /*
         * Car le root dans GameView est un pane et non un stackpane, on doit alors
         * adapter manuellement la taille et la position du Pane de gameOver
         */
        this.root.setOpacity(0.9);
        root.setLayoutX(game.getLayoutX());
        root.setLayoutY(game.getLayoutY());
        root.setPrefWidth(game.getWidth());
        root.setPrefHeight(game.getHeight());
        this.root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(10);
        new GameOverController(p, this);
    }

    // Getters

    /**
     * Méthode pour obtenir la racine de la vue.
     * 
     * @return La racine de la vue.
     */
    public VBox getRoot() {
        return root;
    }

    /**
     * Méthode pour obtenir le stage principal.
     * 
     * @return Le stage principal.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Méthode pour obtenir le bouton Rejouer.
     * 
     * @return Le bouton Rejouer.
     */
    public Button getBtnReplay() {
        return btnReplay;
    }

    /**
     * Méthode pour obtenir le bouton Quitter.
     * 
     * @return Le bouton Quitter.
     */
    public Button getBtnQuit() {
        return btnQuit;
    }

    /**
     * Méthode pour obtenir le bouton Menu.
     * 
     * @return Le bouton Menu.
     */
    public Button getBtnMenu() {
        return btnMenu;
    }

    /**
     * Méthode pour obtenir la scène.
     * 
     * @return La scène.
     */
    public Scene getScene() {
        return scene;
    }

}