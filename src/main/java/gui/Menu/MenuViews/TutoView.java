package gui.Menu.MenuViews;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.TutoController;
import javafx.scene.Scene;

public class TutoView implements Menu{
    private Stage primaryStage;
    private static StackPane root=new StackPane();
    private static Scene scene= new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private Button btnBack;
    private Button musique;
    
    public TutoView(Stage p) {
        this.primaryStage = p;
        root = new StackPane();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        root.getStyleClass().add("root");
        this.btnBack = createButton("Retour", 0, 200);
        this.musique=createButton("musique", 0, 100);
        root.getChildren().add(btnBack);
        root.getChildren().add(musique);
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
    public Button getMusique(){
        return musique;
    }
}
