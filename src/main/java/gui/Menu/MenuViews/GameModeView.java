package gui.Menu.MenuViews;

import gui.ViewPosition;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.GameModeController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class GameModeView implements Menu, ViewPosition {

    private Stage primaryStage;
    private VBox root;
    private Scene scene;

    private Button stagesButton;
    private Button customGameButton;
    private Button backButton;

    public GameModeView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new VBox(45);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        stagesButton = createButton("Niveaux", 0, 0);
        customGameButton = createButton("Personnaliser", 0, 0);
        backButton = createButton("Retour", 0, 0);
        root.getChildren().addAll(stagesButton, customGameButton, backButton);
        new GameModeController(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getStagesButton() {
        return stagesButton;
    }

    public Button getCustomGameButton() {
        return customGameButton;
    }

    public Button getBackButton() {
        return backButton;
    }

}