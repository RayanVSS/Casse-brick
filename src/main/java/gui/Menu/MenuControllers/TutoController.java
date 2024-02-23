package gui.Menu.MenuControllers;

import gui.Menu.SceneManager;
import gui.Menu.MenuViews.TutoView;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Classe TutoController qui gère les interactions de l'utilisateur avec la vue
 * TutoView.
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
    public TutoController(Stage p,TutoView view) { 
        this.view = view;
        this.view.getBtnBack().setOnAction(e -> back());
    }

    /**
     * Méthode pour revenir à la vue du menu de démarrage.
     */
    private void back() {
        Platform.runLater(() -> {
            view.getSceneManager().changeScene(view.getPrimaryStage(), "StartMenuView");
        });
        //view.getPrimaryStage().setScene(new StartMenuView(view.getPrimaryStage(),sceneManager).getScene());
        //new StartMenuView(view.getPrimaryStage());
    }
}