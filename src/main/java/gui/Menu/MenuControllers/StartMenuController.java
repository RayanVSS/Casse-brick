package gui.Menu.MenuControllers;

import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.StartMenuView;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Classe contrôleur pour le menu de démarrage.
 * Elle gère les interactions de l'utilisateur avec la vue StartMenuView.
 * Elle permet de démarrer le jeu, afficher les options, quitter le jeu et
 * afficher le tutoriel.
 * 
 * @see StartMenuView
 * @author Benmalek Majda
 */
public class StartMenuController {
    private StartMenuView view;

    /**
     * Constructeur de StartMenuController.
     * 
     * @param p Le stage principal sur lequel le menu de démarrage est affiché.
     */
    public StartMenuController(Stage p, StartMenuView view) {
        this.view = view;
        this.view.getBtnPlay().setOnAction(e -> Platform.runLater(() -> {
            play();
        }));
        this.view.getBtnOptions().setOnAction(e -> Platform.runLater(() -> {
            options();
        }));
        this.view.getBtnQuit().setOnAction(e -> Platform.runLater(() -> {
            quit();
        }));
        this.view.getBtnTuto().setOnAction(e -> Platform.runLater(() -> {
            tuto();
        }));
        this.view.getBtnSave().setOnAction(e -> Platform.runLater(() -> {
            Sauvegarde();
        }));
    }

    /**
     * Méthode pour démarrer le jeu.
     */
    private void play() {
        Platform.runLater(() -> {
            new GameView(view.getPrimaryStage(), 1);
        });
        
    }

    /**
     * Méthode pour afficher les options.
     */
    private void options() {
        // view.getRoot().getChildren().clear();
        // view.getPrimaryStage().setScene(new
        // OptionsView(view.getPrimaryStage()).getScene());
        // //new OptionsController(view.getPrimaryStage(), new
        // OptionsView(view.getPrimaryStage()));
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "OptionsView");
        });
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
        // view.getRoot().getChildren().clear();
        // new TutoView(view.getPrimaryStage());
        // new TutoController(view.getPrimaryStage());
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "TutoView");
        });
    }

    /**
     * Méthode pour sauvegarder le jeu.
     */
    private void Sauvegarde() {
        // view.getRoot().getChildren().clear();
        // new SaveView(view.getPrimaryStage());
        // new SaveController(view.getPrimaryStage());
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "SaveView");
        });
    }

}