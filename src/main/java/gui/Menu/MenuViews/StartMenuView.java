package gui.Menu.MenuViews;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

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
    public VBox getRoot() {
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
