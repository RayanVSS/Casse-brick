package gui;

import gui.Menu.StartMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    protected Stage primaryStage;
    Scene scene;
    protected StackPane root = new StackPane();

    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;

        primaryStage.setTitle("Casse Brique");
        Label label = new Label("Implementation du Menu");
        root.getChildren().add(label);
        StackPane.setMargin(label, new Insets(0, 100, 50, 0));

        StartMenu startMenu = new StartMenu(primaryStage);
        primaryStage.setScene(startMenu.getScene());
        primaryStage.show();
        primaryStage.getOnCloseRequest();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
