package gui.Menu;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;
import gui.GameView;
import javafx.geometry.Insets;
import javafx.scene.Scene;

public class GameOver implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private StartMenu startMenu;

    public GameOver(Stage p) {
        super();
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.root.setStyle("-fx-background-color: #273654;");
    }

    public void over() {
        root.getChildren().clear();
        Button btnReplay = createButton("Rejouer", 0, 100);
        Button btnQuit = createButton("Quitter", 0, 200);
        Button btnMenu = createButton("Menu", 0, 300);

        btnReplay.setOnAction(e -> {
            root.getChildren().clear();
            GameView game = new GameView(primaryStage, 1);
            game.animation();
        });

        btnQuit.setOnAction(e -> {
            primaryStage.close();
        });

        btnMenu.setOnAction(e -> {
            root.getChildren().clear();
            startMenu = new StartMenu(primaryStage);
            startMenu.Menu();
        });

        root.getChildren().addAll(btnReplay, btnQuit, btnMenu);
        primaryStage.setScene(scene);
    }

}
