package gui.Menu.MenuViews;

import gui.Menu.MenuControllers.GameCustomizerController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class GameCustomizerView implements Menu {

    private Stage primaryStage;
    private HBox root;
    private Scene scene;

    private Button backButton;

    public GameCustomizerView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new HBox(45);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        backButton = createButton("Retour", 0, 0);
        new GameCustomizerController(this);
    }
}
