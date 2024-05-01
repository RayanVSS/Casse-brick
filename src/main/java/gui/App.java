package gui;

import gui.Menu.MenuManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import save.PlayerData;
import save.Sauvegarde;
import utils.Sound.BallSound;
import utils.Sound.BonusSound;
import utils.Sound.ClickSound;
import utils.Sound.GameOverSound;
import utils.Sound.LevelUpSound;
import utils.Sound.MalusSound;
import utils.Sound.Music;

public class App extends Application {

    protected Stage primaryStage;
    public static MenuManager menuManager = new MenuManager();
    private Sauvegarde sauvegarde = new Sauvegarde();
    public static ClickSound clickSoundPlayer;
    public static GameOverSound gameOverS;
    public static Music music;
    public static BallSound ballSound;
    public static BonusSound bonusSound;
    public static MalusSound malusSound;
    public static LevelUpSound levelUp;

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
                bonusSound = new BonusSound();
                malusSound = new MalusSound();
                levelUp = new LevelUpSound();

                primaryStage = p;
                Image icon = new Image("icon.png");
                primaryStage.getIcons().add(icon);
                primaryStage.setResizable(false);
                primaryStage.setTitle("Casse Brique");

                menuManager.preCreateAllView(primaryStage);

                primaryStage.setScene(menuManager.getScene("StartMenuView"));

                primaryStage.show();
                primaryStage.setOnCloseRequest(event -> {
                    autoSaveAndQuit();
                });
            }
        });
    }

    public void autoSaveAndQuit() {
        sauvegarde.autoSave();
        primaryStage.close();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
