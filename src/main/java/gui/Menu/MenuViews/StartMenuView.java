package gui.Menu.MenuViews;

import gui.Menu.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;

public class StartMenuView implements Menu{
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button btnPlay;
    private Button btnOptions;
    private Button btnQuit;

    public StartMenuView(Stage p) {
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.root.setStyle("-fx-background-color: #273654;");
        this.btnPlay = createButton("Jouer", 0, 250);
        this.btnOptions = createButton("Options", 0, 150);
        this.btnQuit = createButton("Quitter", 0, 50);
        root.getChildren().addAll(btnPlay, btnOptions, btnQuit);
        primaryStage.setScene(scene);
    }

    // getters pour les boutons et autres éléments de la vue
    public StackPane getRoot() {
        return root;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public Button getBtnPlay() {
        return btnPlay;
    }
    public Button getBtnOptions() {
        return btnOptions;
    }
    public Button getBtnQuit() {
        return btnQuit;
    }
}

