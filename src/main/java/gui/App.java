package gui;

import gui.Menu.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import save.Sauvegarde;

public class App extends Application {

    protected Stage primaryStage;
    public static SceneManager sceneManager=new SceneManager();
    private Sauvegarde sauvegarde = new Sauvegarde();

    @Override
    public void start(Stage p) throws Exception {
        //chargement de la derniere sauvegarde
        sauvegarde.SetupLastSave();

        this.primaryStage = p;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Casse Brique");
        sceneManager.createStartMenuViewScene(primaryStage);
        sceneManager.createOptionsViewScene(primaryStage);
        sceneManager.createSaveViewScene(primaryStage);
        sceneManager.createTutoViewScene(primaryStage);

        primaryStage.setScene(sceneManager.getScene("StartMenuView"));

        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
