package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gui.GraphicsFactory.ConsoleView;
import gui.Menu.MenuManager;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
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

    protected static Stage primaryStage;
    public static MenuManager menuManager = new MenuManager();
    public static Sauvegarde sauvegarde = new Sauvegarde();
    public static ClickSound clickSoundPlayer;
    public static GameOverSound gameOverS;
    public static Music music;
    public static BallSound ballSound;
    public static BonusSound bonusSound;
    public static MalusSound malusSound;
    public static LevelUpSound levelUp;

    @Override
    public void start(Stage p) throws Exception {
        try{
            Font.loadFont(new FileInputStream("src/main/ressources/font/blabla.ttf"),10);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        if (Font.getFamilies().contains("Kode Mono")) {
            System.out.println("La police est chargée avec succès.");
        } else {
            System.out.println("La police n'est pas chargée.");
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Console.init();
                ConsoleView.getInstance().registerFocusStage(p);
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
                menuManager.changeScene(p, "StartMenuView");

                primaryStage.show();
                primaryStage.setOnCloseRequest(event -> {
                    event.consume();
                    autoSaveAndQuit1();
                });
            }
        });
    }

    public static void autoSaveAndQuit1() { // Rapide, ne pas voir l'exécution 
        sauvegarde.autoSave();
        primaryStage.close();
        Platform.exit();
        System.exit(0);
    }

    public static void autoSaveAndQuit2() { // Lent, voir l'exécution 
        sauvegarde.autoSave();
        PauseTransition pause = new PauseTransition(Duration.seconds(1.0));
        pause.setOnFinished(event -> {
            primaryStage.close();
            Platform.exit();
            System.exit(0);
        });
        pause.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
