package gui.Menu.MenuViews;

import gui.Menu.Menu;
import gui.Menu.MenuControllers.InfinityModeController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InfinityModeView implements Menu {
    private Stage primaryStage;
    private VBox root;
    private Scene scene;
    private Button backButton;

    public InfinityModeView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new VBox(45);
        scene = new Scene(root, 800, 600);
        backButton = createButton("Retour", 0, 0);
        root.getChildren().addAll(backButton);
        new InfinityModeController(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

}
