package gui;

import gui.Menu.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    protected Stage primaryStage;
    public static SceneManager sceneManager=new SceneManager();

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

        primaryStage.centerOnScreen();
        primaryStage.toFront();
        primaryStage.show();
        primaryStage.getOnCloseRequest();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
