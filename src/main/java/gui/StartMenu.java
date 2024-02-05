package gui;

import gui.App;
import gui.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class StartMenu extends App {
    private Scene scene;
    private Stage primaryStage;
    private StackPane root = new StackPane();

    public StartMenu() {
        super();

        // TODO implement here
        Button bouton1 = new Button("Jouer");

        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            new GameView(primaryStage, 1);
        });

        Button bouton2 = new Button("Options");

        bouton2.setOnAction(e -> {
            Options();
        });

        root.getChildren().add(bouton1);
        root.setMargin(bouton1, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(bouton2);
        root.setMargin(bouton2, new javafx.geometry.Insets(0, 100, 200, 000));

    }

    public void menu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();

        Button btn = new Button();
        btn.setText("Start Game");
        btn.setOnAction(e -> {
            new GameView(primaryStage, 1);
        });

        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
