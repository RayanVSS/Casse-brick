package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{

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
        primaryStage.setTitle("Casse Brique");
        primaryStage.setScene(scene);
        primaryStage.show();
        Menu();
    }

    public void Menu() {
        // TODO implement here
        Button bouton1 = new Button("Jouer");


        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            new GameView(primaryStage,1);
        });

        Button bouton2 = new Button("Options");

        bouton2.setOnAction(e -> {
            Options();
        });

        
        root.getChildren().add(bouton1);
        root.setMargin(bouton1, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(bouton2);
        root.setMargin(bouton2, new javafx.geometry.Insets(0, 100, 200, 000));

    }

    public void Options() {
        // TODO implement here
        Label label = new Label("Implementation des Options");
        root.getChildren().clear();
        root.getChildren().add(label);
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
