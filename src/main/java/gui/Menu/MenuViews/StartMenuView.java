package gui.Menu.MenuViews;

import gui.ImageLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.*;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;

public class StartMenuView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button btnPlay;
    private Button btnOptions;
    private Button btnQuit;
    private Button btnTuto;
    private Button btnSave;
    private Image background;

    public StartMenuView(Stage p) {
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.root.setStyle("-fx-background-color: #273654;");
        this.btnPlay = createButton("Jouer", 0, 300);
        this.btnOptions = createButton("Options", 0, 200);
        this.btnTuto = createButton("Tutoriel", 0, 100);
        this.btnQuit = createButton("Quitter", 0, 0);
        this.btnSave = createButton("Sauvegarder", 0, 400);
        root.getChildren().addAll(btnPlay, btnOptions, btnQuit, btnTuto,btnSave);
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

    public Button getBtnTuto() {
        return btnTuto;
    }

    public Button getBtnSave() {
        return btnSave;
    }
}
