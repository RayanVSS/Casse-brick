package gui.Menu.MenuViews;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import gui.Menu.SceneManager;
import gui.Menu.MenuControllers.TutoController;
import javafx.scene.Scene;

public class TutoView extends Menu {
    private Stage primaryStage;
    private static StackPane root=new StackPane();
    private static Scene scene= new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    
    private Button btnBack;
    
    public TutoView(Stage p, SceneManager sceneManager) {
        super(p, scene, sceneManager);
        this.primaryStage = p;
        root = new StackPane();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        root.getStyleClass().add("root");
        this.btnBack = createButton("Retour", 0, 0);
        root.getChildren().add(btnBack);
        //p.setScene(scene);
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
}
