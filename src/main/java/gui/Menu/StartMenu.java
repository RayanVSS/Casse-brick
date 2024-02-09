package gui.Menu;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import gui.GameView;
import javafx.geometry.Insets;
import javafx.scene.Scene;

public class StartMenu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;

    public StartMenu(Stage p) {
        super();
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    public void Menu() {
        root.getChildren().clear();
        Button btnPlay = new Button("Jouer");
        Button btnOptions = new Button("Options");
        Button btnQuit = new Button("Quitter");
        StackPane.setMargin(btnPlay, new Insets(0, 100, 100, 0));
        StackPane.setMargin(btnOptions, new Insets(0, 100, 200, 000));
        StackPane.setMargin(btnQuit, new Insets(0, 100, 300, 0));
        btnPlay.setOnAction(e -> {
            root.getChildren().clear();
            GameView game = new GameView(primaryStage, 1);
            game.animation();
        });
        btnOptions.setOnAction(e -> {
            Option();
        });
        btnQuit.setOnAction(e -> {
            primaryStage.close();
        });
        root.getChildren().add(btnPlay);
        root.getChildren().add(btnOptions);
        root.getChildren().add(btnQuit);
    }

    public void Option() {
        root.getChildren().clear();
        Label label = new Label("Implementation des Options");
        root.getChildren().add(label);
        StackPane.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));
        Button btnBack = new Button("Retour");
        root.getChildren().add(btnBack);
        StackPane.setMargin(btnBack, new javafx.geometry.Insets(0, -870, -700, 0));
        btnBack.setOnAction(e -> {
            Menu();
            primaryStage.setScene(scene);
        });
    }

    public Scene getScene() {
        Menu();
        return this.scene;
    }
}
