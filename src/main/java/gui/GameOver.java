package gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import javafx.scene.Scene;

public class GameOver extends App {
    private Stage primaryStage;
    private StackPane root = new StackPane();
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private Button btn1 = new Button("Rejouer");

    public GameOver(Stage p) {
        super();

        btn1.setOnAction(e -> {
            root.getChildren().clear();
            new GameView(primaryStage, 1);
        });

        root.getChildren().add(btn1);
        root.setMargin(btn1, new javafx.geometry.Insets(0, 100, 100, 0));
        

    }

    public Scene over() {
        return this.scene;
    }

}
