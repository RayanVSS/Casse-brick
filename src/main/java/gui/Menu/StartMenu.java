package gui.Menu;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import gui.GameView;
import javafx.scene.Scene;

public class StartMenu implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;

    public StartMenu(Stage p) {
        super();
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.root.setStyle("-fx-background-color: #273654;");
    }

    public void Menu() {
        root.getChildren().clear();
        Button btnPlay = createButton("Jouer", 0, 250);
        Button btnOptions = createButton("Options", 0, 150);
        Button btnQuit = createButton("Quitter", 0, 50);
        btnPlay.setOnAction(e -> {
            root.getChildren().clear();
            GameView game = new GameView(primaryStage, 1);
            game.getRoot().setStyle("-fx-background-color: #273654;");
            game.animation();
        });
        btnOptions.setOnAction(e -> {
            Option();
        });
        btnQuit.setOnAction(e -> {
            primaryStage.close();
        });
        root.getChildren().addAll(btnPlay, btnOptions, btnQuit);
        primaryStage.setScene(scene);
    }

    public void Option() {
        root.getChildren().clear();
        Label label = new Label("Implementation des Options");
        root.getChildren().add(label);
        StackPane.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));
        Button btnBack = createButton("Retour", -870, -700);
        root.getChildren().add(btnBack);
        btnBack.setOnAction(e -> {
            Menu();
            primaryStage.setScene(scene);
        });
    }
}
