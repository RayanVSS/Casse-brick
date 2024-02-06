package physics;

import gui.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        root = new StackPane();
        scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Physique");
        primaryStage.setScene(scene);
        primaryStage.show();
        Button bouton1 = new Button("Lancer la simulation");
        Button bouton2 = new Button("Options");

        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            lance();
        });

        bouton2.setOnAction(e -> {
            root.getChildren().clear();
            options();
        });

        root.getChildren().add(bouton1);
        root.setMargin(bouton1, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(bouton2);
        root.setMargin(bouton2, new javafx.geometry.Insets(0, 100, 200, 000));

    }

    public void lance(){

    }

    public void options(){


    }

    public static void main(String[] args) {
        launch(args);
    }
}
