package gui;

import gui.Menu.SceneManager;
import gui.Menu.MenuViews.StartMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    protected Stage primaryStage;
    protected SceneManager sceneManager;

    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Casse Brique");
        SceneManager sceneManager = new SceneManager();
        

        sceneManager.createStartMenuViewScene(primaryStage);
        sceneManager.createOptionsViewScene(primaryStage);
        sceneManager.createSaveViewScene(primaryStage);
        sceneManager.createGameViewScene(primaryStage);
        sceneManager.createTutoViewScene(primaryStage);

        for (Scene scene : sceneManager.getScenes().values()) {
            System.out.println(scene);
        }

        primaryStage.setScene(sceneManager.getScene("StartMenuView"));

        // //new StartMenuView(p);
        // // StartMenuView startMenuView = new StartMenuView(p);
        // // primaryStage.setScene(startMenuView.getScene());
        // StartMenuView startMenuView = new StartMenuView(p);
        // //startMenuView.getSceneManager().addScene("StartMenuView",
        // startMenuView.getScene());
        // primaryStage.setScene(startMenuView.getScene());

        primaryStage.show();
        primaryStage.getOnCloseRequest();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
