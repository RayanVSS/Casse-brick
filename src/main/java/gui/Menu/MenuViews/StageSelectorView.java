package gui.Menu.MenuViews;

import java.util.ArrayList;

import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.BaseView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.StageSelectorController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import save.PlayerData;
import utils.GameConstants;
import utils.ImageLoader;

public class StageSelectorView implements Menu, ViewPosition {

    private Stage primaryStage;
    private BorderPane root;
    private VBox centerBox;
    private HBox bottomBox;
    private Scene scene;

    private GridPane grid;
    private ArrayList<Button> buttons;
    private Button backButton;

    private Image lock = ImageLoader.loadImage("src/main/ressources/cadenaFerme.png");
    private Image unLock = ImageLoader.loadImage("src/main/ressources/cadenaOuvert.png");

    private ConsoleView consoleView;
    private BaseView baseView;
    public static int chap;

    public StageSelectorView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new BorderPane();
        centerBox = new VBox(55);
        bottomBox = new HBox();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        System.out.println("chap " + chap);
        createStagesButtons();

        backButton = createButton("Retour", 0, 0);
        centerBox.getChildren().addAll(grid, backButton);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(100, 0, 0, 0));

        consoleView = ConsoleView.getInstance();
        bottomBox.getChildren().add(consoleView);

        root.setCenter(centerBox);
        root.setBottom(bottomBox);
        baseView = new BaseView(root, centerBox, bottomBox);
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
                if (PlayerData.stagesProgress.getStages()[count - 1].canLoadGameForDisplay()) {
                    ImageView imageView = new ImageView(unLock);
                    imageView.setSmooth(true);
                    imageView.setPreserveRatio(true);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    tmp.setGraphic(imageView);
                } else {
                    ImageView imageView = new ImageView(lock);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setSmooth(true);
                    imageView.setPreserveRatio(true);
                    tmp.setGraphic(imageView);
                }
                tmp.setPrefSize(120, 60);
                grid.add(tmp, col, row);
                buttons.add(tmp);
                count++;
            }
        }
        grid.setAlignment(Pos.CENTER);
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

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public Button getBackButton() {
        return backButton;
    }

    @Override
    public void update() {
        System.out.println("chap " + chap);
        for (int i = 0; i < buttons.size(); i++) {
            if (PlayerData.stagesProgress.getStages()[i + (chap * 9)].canLoadGameForDisplay()) {
                ImageView imageView = new ImageView(unLock);
                imageView.setSmooth(true);
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                buttons.get(i).setGraphic(imageView);
            } else {
                ImageView imageView = new ImageView(lock);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setSmooth(true);
                imageView.setPreserveRatio(true);
                buttons.get(i).setGraphic(imageView);
            }
        }
        baseView.update();
    }
}
