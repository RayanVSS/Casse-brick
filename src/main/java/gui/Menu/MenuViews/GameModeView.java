package gui.Menu.MenuViews;

import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.GameModeController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class GameModeView implements Menu, ViewPosition {

    private Stage primaryStage;

    private BorderPane root;
    private VBox centerBox;
    private HBox bottomBox;
    private Scene scene;

    private Button stagesButton;
    private Button customGameButton;
    private Button backButton;
    private ConsoleView consoleView;

    public GameModeView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new BorderPane();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        centerBox = new VBox(45);
        stagesButton = createButton("Niveaux", 0, 0);
        customGameButton = createButton("Personnaliser", 0, 0);
        backButton = createButton("Retour", 0, 0);
        centerBox.getChildren().addAll(stagesButton, customGameButton, backButton);
        centerBox.setAlignment(Pos.CENTER);

        bottomBox = new HBox();
        consoleView = ConsoleView.getInstance();

        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        new GameModeController(this);
    }

    @Override
    public void moveConsoleView() {
        bottomBox.getChildren().add(consoleView);
    }

    @Override
    public void handleDynamicAction() {
        consoleView.setDynamicFocus(scene);
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