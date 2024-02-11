package gui.Menu.MenuViews;

import gui.Menu.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public GameOverView(Stage p) {
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.root.setStyle("-fx-background-color: #273654;");
        this.btnReplay = createButton("Rejouer", 0, 100);
        this.btnQuit = createButton("Quitter", 0, 200);
        this.btnMenu = createButton("Menu", 0, 300);
        root.getChildren().addAll(btnReplay, btnQuit, btnMenu);
        primaryStage.setScene(scene);
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
}
