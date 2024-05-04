package gui;

import gui.GraphicsFactory.ConsoleView;
import gui.Menu.MenuManager;
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

public class App extends Application {

    public static Stage primaryStage;
    public static MenuManager menuManager = new MenuManager();
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
                Console.init();
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

                menuManager.preCreateAllView(primaryStage);
                menuManager.changeScene(p, "StartMenuView");

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
