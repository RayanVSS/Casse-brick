package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.SaveView;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.*;
import save.Sauvegarde;
import utils.Sound.ClickSound;

import static utils.GameConstants.LAST_SAVE;

public class SaveController {
    private SaveView view;
    private Sauvegarde sauvegarde = new Sauvegarde();
    private Map<String, Object> donnees = new HashMap<>();
    private ClickSound click = App.clickSoundPlayer;

    public SaveController(Stage p, SaveView view) {
        this.view = view;
        this.view.getBtnBack().setOnAction(e ->{ 
            click.play();
            back();
        });
        this.view.getBtnload().setOnAction(e -> {
            click.play();
            load();
        });
        this.view.getBtnsave().setOnAction(e -> {
            click.play();
            save();
        });
        this.view.getBtndelete().setOnAction(e -> {
            click.play();
            delete();
        });
        this.view.getBtnOK().setOnAction(e -> {
            click.play();
            ok();
        });
        this.view.getResetSave().setOnAction(e -> {
            click.play();
            resetSave();
        });
    }

    private void back() {
        // new StartMenuView(view.getPrimaryStage());
        Platform.runLater(() -> {
            App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
        });
    }

    private void save() {
        if (LAST_SAVE.equals("")) {
            view.afficherMessage("vous n'etes pas connecté a une sauvegarde");
            return;
        } else {
            // sa marche pas
            String saveName = LAST_SAVE.replace(".json", "");
            System.out.println(saveName);
            sauvegarde.sauvegarderToutesDonnees(saveName);
            view.afficherMessage("Sauvegarde '" + LAST_SAVE + "' effectuée avec succès");
        }

    }

    private void load() {
        String selectedSauvegarde = view.getListSave().getValue();
        System.out.println(selectedSauvegarde);

        if (selectedSauvegarde != null) { // si une sauvegarde est sélectionnée
            sauvegarde.chargerToutesDonnees(selectedSauvegarde); // Charger la sauvegarde sélectionnée dans le fichier
            view.afficherMessage("Sauvegarde '" + selectedSauvegarde + "' chargée avec succès"); // Afficher un message
                                                                                                 // de confirmation
        } else {
            view.afficherMessage("Veuillez sélectionner une sauvegarde à charger"); // Afficher un message d'erreur
        }
    }

    private void delete() {
        String selectedSauvegarde = view.getListSave().getValue();
        if (selectedSauvegarde != null) { // si une sauvegarde est sélectionnée
            sauvegarde.supprimerSauvegarde(selectedSauvegarde);// Supprimer la sauvegarde sélectionnée dans le fichier
            view.getListSave().getItems().remove(selectedSauvegarde); // Supprimer la sauvegarde sélectionnée dans la
                                                                      // ComboBox
            view.afficherMessage("Sauvegarde " + selectedSauvegarde + " supprimée avec succès"); // Afficher un message
                                                                                                 // de confirmation
        } else {
            view.afficherMessage("Veuillez sélectionner une sauvegarde à supprimer"); // Afficher un message d'erreur
        }
    }

    private void ok() {
        String nomUtilisateur = view.getNameSave().getText(); // Récupérer le nom de l'utilisateur
        sauvegarde.sauvegarderToutesDonnees(nomUtilisateur); // Sauvegarder les options du jeu
        if (!view.getListSave().getItems().contains(nomUtilisateur + ".json")) // Si le nom de l'utilisateur n'est pas
                                                                               // déjà dans la ComboBox
            view.getListSave().getItems().add(nomUtilisateur + ".json"); // Ajouter le nom de l'utilisateur à la
                                                                         // ComboBox
        view.afficherMessage("c'est bon"); // Afficher un message de confirmation
    }

    private void resetSave() {
        view.afficherMessage("il n'y a plus de sauvegarde par defaut"); // Afficher un message de confirmation
        sauvegarde.resetLastSave(); // Réinitialiser la dernière sauvegarde
    }

}
