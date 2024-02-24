package gui.Menu.MenuControllers;


import gui.GameView;
import gui.Menu.MenuViews.GameOverView;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Classe GameOverController qui gère les interactions de l'utilisateur avec la vue GameOverView.
 * Elle permet de rejouer, quitter ou revenir au menu.
 * @see GameOverView
 * @author Benmalek Majda
 */
public class GameOverController {
    private GameOverView view;

    /**
     * Constructeur de GameOverController.
     * @param p Le stage principal sur lequel la vue de fin de partie est affichée.
     * @param gameView La vue de fin de partie.
     */
    public GameOverController(Stage p, GameOverView gameView) {
        this.view=gameView;
        this.view.getBtnReplay().setOnAction(e -> replay());
        this.view.getBtnQuit().setOnAction(e -> quit());
        this.view.getBtnMenu().setOnAction(e -> menu());
    }

    /**
     * Méthode pour rejouer le jeu. Elle efface tous les enfants de la racine de la vue et lance une nouvelle animation de jeu.
     */
    private void replay() {
        //view.getRoot().getChildren().clear();
        Platform.runLater(() -> {
            //view.getSceneManager().changeScene(view.getPrimaryStage(), "GameView");
            new GameView(view.getPrimaryStage(), 1, view.getSceneManager());
            //game.animation();
        });
        // view.getGameView().getRoot().getChildren().clear();
        // GameView game = new GameView(view.getPrimaryStage(), 1, view.getSceneManager());
        // game.animation();
    }

    /**
     * Méthode pour quitter le jeu. Elle ferme le stage principal.
     */
    private void quit() {
        view.getPrimaryStage().close();
    }

    /**
     * Méthode pour revenir au menu. Elle efface tous les enfants de la racine de la vue et lance le contrôleur du menu de démarrage.
     */
    private void menu() {
        // view.getRoot().getChildren().clear();
        // StartMenuView s=new StartMenuView(view.getPrimaryStage());
        // view.getPrimaryStage().setScene(s.getScene());
        Platform.runLater(() -> {
            view.getSceneManager().changeScene(view.getPrimaryStage(), "StartMenuView");
        });
        
    }
}
