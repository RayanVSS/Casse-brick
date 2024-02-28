package gui;

import gui.Menu.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import save.Sauvegarde;
import static utils.GameConstants.LAST_SAVE;

public class App extends Application {

    protected Stage primaryStage;
    public static SceneManager sceneManager=new SceneManager();
    private Sauvegarde sauvegarde = new Sauvegarde();

    @Override
    public void start(Stage p) throws Exception {
        //chargement de la derniere sauvegarde
        System.out.println("Chargement de la derniere sauvegarde");
        sauvegarde.chargerLastSave();
        System.out.println(LAST_SAVE + "AAAAAAAAAAAAAAAAAAAAAAAA");
        sauvegarde.chargerOptionsDuJeu(LAST_SAVE);

        this.primaryStage = p;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Casse Brique");
        sceneManager.createStartMenuViewScene(primaryStage);
        sceneManager.createOptionsViewScene(primaryStage);
        sceneManager.createSaveViewScene(primaryStage);
        sceneManager.createTutoViewScene(primaryStage);

        primaryStage.setScene(sceneManager.getScene("StartMenuView"));

        primaryStage.show();
        primaryStage.getOnCloseRequest();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
