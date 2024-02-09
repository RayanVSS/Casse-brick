package gui.Menu;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;
import javafx.geometry.Insets;
import javafx.scene.Scene;

public class GameOver {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private StartMenu startMenu;

    public GameOver(Stage p) {
        super();
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    public void over() {
        root.getChildren().clear();
        Button btnReplay = new Button("Rejouer");
        Button btnQuit=new Button("Quitter");
        StackPane.setMargin(btnReplay, new Insets(0, 100, 100, 0));
        StackPane.setMargin(btnQuit, new Insets(0, 100, 200, 000));
        btnReplay.setOnAction(e -> {
            root.getChildren().clear();
            startMenu = new StartMenu(primaryStage);
            primaryStage.setScene(startMenu.getScene());
        });
        btnQuit.setOnAction(e -> {
            primaryStage.close();
        });
        root.getChildren().add(btnReplay);
        root.getChildren().add(btnQuit);
        primaryStage.setScene(scene);
    }
}
