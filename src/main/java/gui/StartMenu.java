package gui;

import config.Game;
import gui.App;
import gui.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import javafx.scene.Scene;

public class StartMenu extends App {
    private Stage primaryStage;
    private StackPane root = new StackPane();
    private Scene scene=new Scene(root,GameConstants.DEFAULT_WINDOW_WIDTH,GameConstants.DEFAULT_WINDOW_HEIGHT);
    private Button btn1=new Button("Jouer");
    private Button btn2=new Button("Options");

    public StartMenu(Stage p) {
        super();

        btn1.setOnAction(e -> {
            root.getChildren().clear();
            new GameView(p, 1);
        });

        btn2.setOnAction(e -> {
            Options();
        });

        root.getChildren().add(btn1);
        root.setMargin(btn1, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(btn2);
        root.setMargin(btn2, new javafx.geometry.Insets(0, 100, 200, 000));

    }

    public Scene menu() {
        return this.scene;
    }

}
