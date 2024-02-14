package gui.Menu.MenuViews;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;
import javafx.scene.Scene;

public class TutoView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button btnBack;
    
    public TutoView(Stage p) {
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.root.setStyle("-fx-background-color: #273654;");
        this.btnBack = createButton("Retour", 0, 0);
        this.root.getChildren().add(btnBack);
        primaryStage.setScene(scene);
    }
    public Button getBtnBack() {
        return btnBack;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
