package gui.Menu.MenuControllers;

import config.Game;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.GameOverView;
import javafx.application.Platform;
import utils.Sound.ClickSound;

/**
 * Classe GameOverController qui gère les interactions de l'utilisateur avec la vue GameOverView.
 * Elle permet de rejouer, quitter ou revenir au menu.
 * @see GameOverView
 * @author Benmalek Majda
 */
public class GameOverController {
    private GameOverView view;
    private ClickSound click = App.clickSoundPlayer;

    /**
     * Constructeur de GameOverController.
     * @param p Le stage principal sur lequel la vue de fin de partie est affichée.
     * @param gameView La vue de fin de partie.
     */
    public GameOverController(GameOverView gameView) {
        this.view = gameView;
        this.view.getBtnReplay().setOnAction(e -> {
            click.play();
            replay();
        });
        this.view.getBtnQuit().setOnAction(e -> {
            click.play();
            quit();
        });
        this.view.getBtnMenu().setOnAction(e -> {
            click.play();
            menu();
        });
    }

    /**
     * Méthode pour rejouer le jeu. Elle efface tous les enfants de la racine de la vue et lance une nouvelle animation de jeu.
     */
    private void replay() {
        Platform.runLater(() -> {

            if (view.getGame().getStageLevel().isCustomGame()) {
                view.getGame().getStageLevel().getGameReinitializer().createGame();
            } else {
                new GameView(view.getPrimaryStage(), view.getGame().getStageLevel());
            }

        });
    }

    /**
     * Méthode pour quitter le jeu. Elle ferme le stage principal.
     */
    private void quit() {
        view.getPrimaryStage().close();
        Platform.exit();
        System.exit(0);
    }

    /**
     * Méthode pour revenir au menu. Elle efface tous les enfants de la racine de la vue et lance le contrôleur du menu de démarrage.
     */
    private void menu() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
        });
    }
}
