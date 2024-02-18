package gui.Menu.MenuControllers;

import gui.GameView;
import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.TutoView;
import gui.Menu.MenuViews.SaveView;

import javafx.stage.Stage;

/**
 * Classe contrôleur pour le menu de démarrage.
 * @author Benmalek Majda
 */
public class StartMenuController {
    private StartMenuView view;

    /**
     * Constructeur de StartMenuController.
     * 
     * @param p Le stage principal sur lequel le menu de démarrage est affiché.
     */
    public StartMenuController(Stage p) {
        this.view = new StartMenuView(p);
        this.view.getBtnPlay().setOnAction(e -> play());
        this.view.getBtnOptions().setOnAction(e -> options());
        this.view.getBtnQuit().setOnAction(e -> quit());
        this.view.getBtnTuto().setOnAction(e -> tuto());
        this.view.getBtnSave().setOnAction(e -> Sauvegarde());
    }

    /**
     * Méthode pour démarrer le jeu.
     */
    private void play() {
        view.getRoot().getChildren().clear();
        GameView game = new GameView(view.getPrimaryStage(), 1);
        game.getRoot().setStyle("-fx-background-color: #E0ECF5;");
        game.animation();
    }

    /**
     * Méthode pour afficher les options.
     */
    private void options() {
        view.getRoot().getChildren().clear();
        new OptionsView(view.getPrimaryStage());
        new OptionsController(view.getPrimaryStage());
    }

    /**
     * Méthode pour quitter le jeu.
     */
    private void quit() {
        view.getPrimaryStage().close();
    }

    /**
     * Méthode pour afficher le tutoriel.
     */
    private void tuto() {
        view.getRoot().getChildren().clear();
        new TutoView(view.getPrimaryStage());
        new TutoController(view.getPrimaryStage());
    }

    /**
     * Méthode pour sauvegarder le jeu.
     */
    private void Sauvegarde() {
        view.getRoot().getChildren().clear();
        new SaveView(view.getPrimaryStage());
        new SaveController(view.getPrimaryStage());
    }

}