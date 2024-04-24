package gui;

import gui.Menu.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import save.PlayerData;
import save.Sauvegarde;
import utils.Sound.BallSound;
import utils.Sound.ClickSound;
import utils.Sound.GameOverSound;
import utils.Sound.Music;
import utils.GameConstants;

public class App extends Application {

    protected Stage primaryStage;
    public static SceneManager sceneManager = new SceneManager();
    private Sauvegarde sauvegarde = new Sauvegarde();
    public static ClickSound clickSoundPlayer;
    public static GameOverSound gameOverS;
    public static Music music;
    public static BallSound ballSound;

    @Override
    public void start(Stage p) throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                PlayerData.initPlayerData();
                // chargement de la derniere sauvegarde
                sauvegarde.setupLastSave();

                // initialisation des sons
                clickSoundPlayer = new ClickSound();
                gameOverS = new GameOverSound();
                music = new Music();
                ballSound = new BallSound();

                primaryStage = p;
                Image icon = new Image("icon.png");
                primaryStage.getIcons().add(icon);
                primaryStage.setResizable(false);
                primaryStage.setTitle("Casse Brique");

                sceneManager.preCreateAllView(primaryStage);

                primaryStage.setScene(sceneManager.getScene("StartMenuView"));

                primaryStage.show();
                primaryStage.setOnCloseRequest(event -> {
                    autoSave();
                });
            }
        });
    }

    public void autoSave() {
        String saveName;
        if (GameConstants.LAST_SAVE.equals("")) {
            saveName = "autoTempSave";
        } else {
            saveName = GameConstants.LAST_SAVE.replace(".json", "");
        }
        sauvegarde.sauvegarderToutesDonnees(saveName);
        System.out.println("Sauvegarde automatique de '" + GameConstants.LAST_SAVE + "' effectuée avec succès");
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
