package gui;

import gui.Menu.StartMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    protected Stage primaryStage;
    private Scene scene;
    protected StackPane root = new StackPane();

    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Casse Brique");

        StartMenu startMenu = new StartMenu(primaryStage);
        startMenu.Menu();
        primaryStage.show();
        primaryStage.getOnCloseRequest();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
