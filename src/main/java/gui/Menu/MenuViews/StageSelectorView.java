package gui.Menu.MenuViews;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StageSelectorView implements Menu {

    private Stage primaryStage;
    private GridPane root;
    private Scene scene;

    private ArrayList<Button> stagesButtons;
    private Button backButton;

    public StageSelectorView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public ArrayList<Button> getStagesButtons() {
        return stagesButtons;
    }

    public Button getBackButton() {
        return backButton;
    }
}
