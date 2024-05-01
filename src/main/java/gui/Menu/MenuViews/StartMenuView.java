package gui.Menu.MenuViews;

import gui.GraphicsFactory.ConsoleView;
import gui.GraphicsFactory.ProfileView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.StartMenuController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private BorderPane root = new BorderPane();

    private HBox topBox = new HBox();
    private VBox centerBox = new VBox();
    private HBox bottomBox = new HBox();
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH,
            GameConstants.DEFAULT_WINDOW_HEIGHT);

    private ProfileView profileView;
    private Button btnPlay;
    private Button btnOptions;
    private Button btnQuit;
    private Button btnSave;
    private Label title;
    private ConsoleView consoleView;

    /**
     * Constructeur de StartMenuView.
     * 
     * @param p Le stage principal sur lequel le menu de démarrage est affiché.
     */
    public StartMenuView(Stage p) {

        this.primaryStage = p;

        createTop();
        createCenter();
        createBottom();

        root.setTop(topBox);
        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        new StartMenuController(p, this);
    }

    private void createTop() {
        profileView = new ProfileView();
        topBox.getChildren().addAll(profileView);
    }

    private void createCenter() {

        title = createLabel("Casse Brique", 0, 0);
        btnPlay = createButton("Jouer", 0, 0);
        btnOptions = createButton("Options", 0, 0);
        btnSave = createButton("Sauvegarder", 0, 0);
        btnQuit = createButton("Quitter", 0, 0);
        setCenterBoxStyle();
        centerBox.getChildren().addAll(title, btnPlay, btnOptions, btnSave, btnQuit);
    }

    private void setCenterBoxStyle() {
        title.getStyleClass().add("title-style");
        centerBox.getStyleClass().add("root");
        centerBox.setSpacing(10);
        centerBox.setAlignment(Pos.CENTER);
    }

    private void createBottom() {
        consoleView = ConsoleView.getInstance();
        bottomBox.getChildren().addAll(consoleView);
    }

    @Override
    public void update() {
        profileView.update();
        // displayer.update();
    }

    // getters pour les boutons et autres éléments de la vue

    /**
     * Méthode pour obtenir la racine de la vue.
     * 
     * @return La racine de la vue.
     */
    public BorderPane getRoot() {
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
     * Méthode pour obtenir le bouton Sauvegarder.
     * 
     * @return Le bouton Sauvegarder.
     */
    public Button getBtnSave() {
        return btnSave;
    }

    public Scene getScene() {
        return scene;
    }

}