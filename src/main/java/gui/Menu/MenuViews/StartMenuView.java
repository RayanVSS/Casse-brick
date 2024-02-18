package gui.Menu.MenuViews;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Classe StartMenuView qui implémente l'interface Menu pour représenter la vue
 * du menu de démarrage.
 * Elle contient les éléments graphiques du menu de démarrage.
 * 
 * @author Benmalek Majda
 */
public class StartMenuView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private VBox root;
    private Button btnPlay;
    private Button btnOptions;
    private Button btnQuit;
    private Button btnTuto;
    private Button btnSave;
    private Label title;

    /**
     * Constructeur de StartMenuView.
     * 
     * @param p Le stage principal sur lequel le menu de démarrage est affiché.
     */
    public StartMenuView(Stage p) {
        this.primaryStage = p;
        this.root = new VBox();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        this.title = createLabel("Casse Brique", 0, 0, 50);
        this.btnPlay = createButton("Jouer", 0, 0);
        this.btnOptions = createButton("Options", 0, 0);
        this.btnTuto = createButton("Tutoriel", 0, 0);
        this.btnSave = createButton("Sauvegarder", 0, 0);
        this.btnQuit = createButton("Quitter", 0, 0);

        root.setStyle("-fx-background-color: #273654;");
        root.getChildren().addAll(title, btnPlay, btnOptions, btnSave, btnTuto, btnQuit);
        root.setSpacing(10);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        primaryStage.setScene(scene);
    }

    // getters pour les boutons et autres éléments de la vue

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
     * Méthode pour obtenir le bouton Jouer.
     * 
     * @return Le bouton Jouer.
     */
    public Button getBtnPlay() {
        return btnPlay;
    }

    /**
     * Méthode pour obtenir le bouton Options.
     * 
     * @return Le bouton Options.
     */
    public Button getBtnOptions() {
        return btnOptions;
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
     * Méthode pour obtenir le bouton Tutoriel.
     * 
     * @return Le bouton Tutoriel.
     */
    public Button getBtnTuto() {
        return btnTuto;
    }

    /**
     * Méthode pour obtenir le bouton Sauvegarder.
     * 
     * @return Le bouton Sauvegarder.
     */
    public Button getBtnSave() {
        return btnSave;
    }
}