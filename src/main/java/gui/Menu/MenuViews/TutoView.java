package gui.Menu.MenuViews;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import gui.Menu.Menu;
import gui.Menu.SceneManager;
import gui.Menu.MenuControllers.TutoController;
import javafx.scene.Scene;

public class TutoView implements Menu{
    private Stage primaryStage;
    private static StackPane root=new StackPane();
    private static Scene scene= new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private SceneManager sceneManager;
    private Button btnBack;
    
    public TutoView(Stage p) {
        this.primaryStage = p;
        root = new StackPane();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        root.getStyleClass().add("root");
        this.btnBack = createButton("Retour", 0, 0);
        root.getChildren().add(btnBack);
        new TutoController(p,this);
    }
    public Button getBtnBack() {
        return btnBack;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public StackPane getRoot() {
        return root;
    }
    public Scene getScene() {
        return scene;
    }
    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
