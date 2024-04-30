package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.StartMenuView;
import javafx.application.Platform;
import javafx.stage.Stage;
import save.Sauvegarde;
import utils.Sound.ClickSound;

/**
 * Classe contrôleur pour le menu de démarrage.
 * Elle gère les interactions de l'utilisateur avec la vue StartMenuView.
 * Elle permet de démarrer le jeu, afficher les options, quitter le jeu.
 * 
 * @see StartMenuView
 * @author Benmalek Majda
 */
public class StartMenuController {
    private StartMenuView view;
    private ClickSound click = App.clickSoundPlayer;

    /**
     * Constructeur de StartMenuController.
     * 
     * @param p Le stage principal sur lequel le menu de démarrage est affiché.
     */
    public StartMenuController(Stage p, StartMenuView view) {
        this.view = view;
        this.view.getBtnPlay().setOnAction(e -> {
            click.play();
            play();
        });
        this.view.getBtnOptions().setOnAction(e -> {
            click.play();
            options();
        });
        this.view.getBtnQuit().setOnAction(e -> {
            click.play();
            quit();
        });
        this.view.getBtnSave().setOnAction(e -> {
            click.play();
            sauvegarde();
        });
        this.view.getBtnBoutique().setOnAction(e -> {
            click.play();
            boutique();
        });
    }

    /**
     * Méthode pour choisir le mode de jeu.
     */
    private void play() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "GameModeView");
        });

    }

    /**
     * Méthode pour afficher les options.
     */
    private void options() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "OptionsView");
        });
    }

    /**
     * Méthode pour quitter le jeu.
     */
    private void quit() {
        Sauvegarde sauvegarde = new Sauvegarde();
        sauvegarde.autoSave();
        view.getPrimaryStage().close();
        Platform.exit();
        System.exit(0);
    }

    /**
     * Méthode pour sauvegarder le jeu.
     */
    private void sauvegarde() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "SaveView");
        });
    }

    /**
     * Méthode pour accéder à la boutique.
     */
    private void boutique() {
        //Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "BoutiqueView");
        //});
    }

}