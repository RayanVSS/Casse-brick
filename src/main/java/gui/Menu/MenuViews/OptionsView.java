package gui.Menu.MenuViews;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;

public class OptionsView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button btnBack;

    public OptionsView(Stage p) {
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        Label label = new Label("Implementation des Options");
        StackPane.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));
        btnBack = createButton("Retour", -870, -700);
        root.getChildren().addAll(btnBack, label);
        root.setStyle("-fx-background-color: #273654;");
        primaryStage.setScene(scene);
    }

    // Getters
    public StackPane getRoot() {
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getBtnBack() {
        return btnBack;
    }

}
