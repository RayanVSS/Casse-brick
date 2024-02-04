package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{

    protected Stage primaryStage;
    Scene scene;
    protected StackPane root =new StackPane();
    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        StartMenu startMenu = new StartMenu();

        Label label = new Label("Implementation du Menu");
        root.getChildren().add(label);
        root.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));
        scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Casse Brique");
        primaryStage.setScene(scene);
        primaryStage.show();
        new StartMenu();

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
