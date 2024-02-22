package gui.Menu.MenuViews;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import javafx.scene.Scene;

public class TutoView extends Menu {
    private Stage primaryStage;
    private static StackPane root=new StackPane();
    private static Scene scene= new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    
    private Button btnBack;
    
    public TutoView(Stage p) {
        super(p, scene);
        this.primaryStage = p;
        root = new StackPane();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        scene.getStylesheets().add("/styles/blue.css");
        root.getStyleClass().add("root");
        this.btnBack = createButton("Retour", 0, 0);
        root.getChildren().add(btnBack);
        p.setScene(scene);
    }
    public Button getBtnBack() {
        return btnBack;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
