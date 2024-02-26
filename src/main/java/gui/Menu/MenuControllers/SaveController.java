package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.SaveView;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.*;
import save.Sauvegarde;

import static utils.GameConstants.LAST_SAVE;


public class SaveController {
    private SaveView view;
    private Sauvegarde sauvegarde = new Sauvegarde();
    private Map<String, Object> donnees = new HashMap<>();


    public SaveController(Stage p , SaveView view) {
        this.view = view;
        this.view.getBtnBack().setOnAction(e -> back());
        this.view.getBtnload().setOnAction(e -> load());
        this.view.getBtnsave().setOnAction(e -> save());
        this.view.getBtndelete().setOnAction(e -> delete());
        this.view.getBtnOK().setOnAction(e -> ok());
        this.view.getResetSave().setOnAction(e -> resetSave());
    }

    private void back() {
        //new StartMenuView(view.getPrimaryStage());
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
        });
    }

    private void save() {
        // TODO
    }

    private void load() {
        String selectedSauvegarde = view.getListSave().getValue();
        System.out.println(selectedSauvegarde);

        if (selectedSauvegarde != null) { //si une sauvegarde est sélectionnée 
            sauvegarde.chargerOptionsDuJeu(selectedSauvegarde); // Charger la sauvegarde sélectionnée dans le fichier
            view.afficherMessage("Sauvegarde '" + selectedSauvegarde + "' chargée avec succès"); // Afficher un message de confirmation
        } else {
            view.afficherMessage("Veuillez sélectionner une sauvegarde à charger"); // Afficher un message d'erreur
        }
    }

    private void delete() {
        String selectedSauvegarde = view.getListSave().getValue();
        if (selectedSauvegarde != null) { //si une sauvegarde est sélectionnée
            sauvegarde.supprimerSauvegarde(selectedSauvegarde);// Supprimer la sauvegarde sélectionnée dans le fichier
            view.getListSave().getItems().remove(selectedSauvegarde); // Supprimer la sauvegarde sélectionnée dans la ComboBox
            view.afficherMessage("Sauvegarde " + selectedSauvegarde + " supprimée avec succès"); // Afficher un message de confirmation
        } else {
            view.afficherMessage("Veuillez sélectionner une sauvegarde à supprimer"); // Afficher un message d'erreur
        }
    }

    private void ok() {
        String nomUtilisateur = view.getNameSave().getText(); // Récupérer le nom de l'utilisateur
        sauvegarde.sauvegarderOptionsDuJeu(nomUtilisateur); // Sauvegarder les options du jeu
        view.afficherMessage("c'est bon"); // Afficher un message de confirmation
    }

    private void resetSave() {
        sauvegarde.resetLastSave(); // Réinitialiser la dernière sauvegarde
    }



    
}
