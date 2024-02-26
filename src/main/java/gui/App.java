package gui;

import java.io.File;
import java.io.FileInputStream;

import gui.Menu.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    protected Stage primaryStage;
    public static SceneManager sceneManager=new SceneManager();
    public static Font maSuperbePolice ;

    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Casse Brique");
        sceneManager.createStartMenuViewScene(primaryStage);
        sceneManager.createOptionsViewScene(primaryStage);
        sceneManager.createSaveViewScene(primaryStage);
        sceneManager.createGameViewScene(primaryStage);
        sceneManager.createTutoViewScene(primaryStage);

        for (Scene scene : sceneManager.getScenes().values()) {
            System.out.println(scene + " " +sceneManager.getSceneName(scene)+" added to primaryStage");
        }

        primaryStage.setScene(sceneManager.getScene("StartMenuView"));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
