package gui.Menu.MenuControllers;

import java.time.Duration;

import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.StartMenuView;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Classe OptionsController qui gère les interactions de l'utilisateur avec la
 * vue OptionsView.
 * Elle permet de modifier les elements modifiables du jeu.
 * 
 * @see OptionsView
 * @author Benmalek Majda
 */
public class OptionsController {
    private OptionsView view;

    /**
     * Constructeur de OptionsController.
     * 
     * @param p Le stage principal sur lequel la vue des options est affichée.
     */
    public OptionsController(Stage p, OptionsView view) {
        this.view = view;
        this.view.getBtnBack().setOnAction(e -> back());
        this.view.getButtonfps().setOnAction(e -> fps());
        this.view.getButtonpath().setOnAction(e -> path());
        this.view.getButtonparticles().setOnAction(e -> particles());
        this.view.getVolumemusic().valueProperty().addListener((observable, oldValue, newValue) -> {
            GameConstants.MUSIC = newValue.intValue();
        });
        this.view.getVolumesound().valueProperty().addListener((observable, oldValue, newValue) -> {
            GameConstants.SOUND = newValue.intValue();
        });
        this.view.getButtonleft().setOnAction(e -> left());
        this.view.getButtonpower().setOnAction(e -> power());
        this.view.getButtonright().setOnAction(e -> right());
    }

    /**
     * Méthode pour revenir à la vue du menu de démarrage.
     */
    private void back() {
        FadeTransition transition = new FadeTransition();
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        
        Platform.runLater(() -> {
            view.getRoot().getChildren().clear();
            transition.play();
            new StartMenuView(view.getPrimaryStage());
        });
    }

    /**
     * Méthode pour gérer l'activation/désactivation du FPS.
     */
    private void fps() {
        if (view.getButtonfps().isSelected()) {
            view.getButtonfps().setText("ON");
        } else {
            view.getButtonfps().setText("OFF");
        }
        GameConstants.FPS = this.view.getButtonfps().isSelected();
    }

    /**
     * Méthode pour gérer l'activation/désactivation du chemin.
     */
    private void path() {
        if (view.getButtonpath().isSelected()) {
            view.getButtonpath().setText("ON");
        } else {
            view.getButtonpath().setText("OFF");
        }
        GameConstants.PATH = this.view.getButtonpath().isSelected();
    }

    /**
     * Méthode pour gérer l'activation/désactivation des particules.
     */
    private void particles() {
        if (view.getButtonparticles().isSelected()) {
            view.getButtonparticles().setText("ON");
        } else {
            view.getButtonparticles().setText("OFF");
        }
        GameConstants.PARTICLES = this.view.getButtonparticles().isSelected();
    }

    /**
     * Méthode pour gérer l'activation de la touche gauche.
     */
    private void left() {
        view.getButtonleft().setText("Appuyez sur une touche...");
        view.getButtonleft().setOnKeyPressed(event -> {
            GameConstants.LEFT = event.getCode();
            view.getButtonleft().setText(GameConstants.LEFT.getName());
            view.getButtonleft().setOnKeyPressed(null); // Désactive la liaison après la configuration
        });
    }

    /**
     * Méthode pour gérer l'activation de la touche d'alimentation.
     */
    private void power() {
        view.getButtonpower().setText("Appuyez sur une touche...");
        view.getButtonpower().setOnKeyPressed(event -> {
            GameConstants.SPACE = event.getCode();
            view.getButtonpower().setText(GameConstants.SPACE.getName());
            view.getButtonpower().setOnKeyPressed(null); // Désactive la liaison après la configuration
        });
    }

    /**
     * Méthode pour gérer l'activation de la touche droite.
     */
    private void right() {
        view.getButtonright().setText("Appuyez sur une touche...");
        view.getButtonright().setOnKeyPressed(event -> {
            GameConstants.RIGHT = event.getCode();
            view.getButtonright().setText(GameConstants.RIGHT.getName());
            view.getButtonright().setOnKeyPressed(null); // Désactive la liaison après la configuration
        });
        ;
    }
}