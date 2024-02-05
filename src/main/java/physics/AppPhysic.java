package physics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppPhysic extends Application {
    
    Stage primaryStage;
    Scene scene;
    StackPane root ;
    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        Label label = new Label("Implementation du Menu");
        root = new StackPane();
        root.getChildren().add(label);
        root.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));
        scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Physique");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
