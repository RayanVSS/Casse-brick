package gui.Menu.MenuControllers;

import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.TutoView;
import javafx.stage.Stage;

/**
 * Classe TutoController qui gère les interactions de l'utilisateur avec la vue
 * TutoView.
 * 
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
    public TutoController(Stage p) {
        this.view = new TutoView(p);
        this.view.getBtnBack().setOnAction(e -> back());
    }

    /**
     * Méthode pour revenir à la vue du menu de démarrage.
     */
    private void back() {
        new StartMenuView(view.getPrimaryStage());
        new StartMenuController(view.getPrimaryStage());
    }
}