package gui;

import gui.Menu.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import save.PlayerData;
import save.Sauvegarde;
import utils.Sound.ClickSound;

public class App extends Application {

    protected Stage primaryStage;
    public static SceneManager sceneManager = new SceneManager();
    private Sauvegarde sauvegarde = new Sauvegarde();
    public static ClickSound clickSoundPlayer = new ClickSound();

    @Override
    public void start(Stage p) throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                PlayerData.initPlayerData();
                // chargement de la derniere sauvegarde
                sauvegarde.setupLastSave();

                primaryStage = p;
                Image icon = new Image("icon.png");
                primaryStage.getIcons().add(icon);
                primaryStage.setResizable(false);
                primaryStage.setTitle("Casse Brique");

                sceneManager.preCreateAllView(primaryStage);

                primaryStage.setScene(sceneManager.getScene("StartMenuView"));

                primaryStage.show();
                primaryStage.setOnCloseRequest(event -> {
                    System.exit(0);
                });
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
