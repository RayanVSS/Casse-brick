package gui.Menu.MenuViews;

import java.util.ArrayList;

import gui.Menu.Menu;
import gui.Menu.MenuControllers.StageSelectorController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class StageSelectorView implements Menu {

    private Stage primaryStage;
    private VBox root;
    private Scene scene;

    private GridPane grid;
    private ArrayList<Button> buttons;
    private Button backButton;

    public StageSelectorView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new VBox(55);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        createStagesButtons();
        backButton = createButton("Retour", 0, 0);
        root.getChildren().addAll(grid, backButton);
        new StageSelectorController(this);
    }

    public void createStagesButtons() {
        grid = new GridPane();
        grid.setHgap(50);
        grid.setVgap(50);
        buttons = new ArrayList<>();
        int count = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button tmp = createButton("" + count, 0, 0);
                tmp.setPrefSize(120, 60);
                grid.add(tmp, col, row);
                buttons.add(tmp);
                count++;
            }
        }
        grid.setAlignment(Pos.CENTER);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public Button getBackButton() {
        return backButton;
    }
}
