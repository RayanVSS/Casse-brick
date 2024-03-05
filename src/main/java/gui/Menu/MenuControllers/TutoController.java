package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.TutoView;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Classe TutoController qui gère les interactions de l'utilisateur avec la vue
 * TutoView.
 * 
 * @see TutoView
 * @author Benmalek Majda
 * 
 */
public class TutoController {
    private TutoView view;

    /**
     * Constructeur de TutoController.
     * 
     * @param p Le stage principal sur lequel la vue du tutoriel est affichée.
     */
    public TutoController(Stage p, TutoView view) {
        this.view = view;
        this.view.getBtnBack().setOnAction(e -> back());
        this.view.getMusique().setOnAction(e -> music());
    }

    /**
     * Méthode pour revenir à la vue du menu de démarrage.
     */
    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
        });
    }

    private void music() {
        try {
            Media sound = new Media(getClass().getResource("/music/test2.mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            System.out.println("musique");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}