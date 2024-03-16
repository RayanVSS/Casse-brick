package gui.Menu;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.SaveView;
import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.TutoView;
import javafx.util.Duration;
import utils.GameConstants;

/**
 * Classe de gestion des scènes dans l'application.
 */
public class SceneManager {
    private Map<String, Scene> scenes = new HashMap<>();

    /**
     * Ajoute une scène à la collection.
     *
     * @param name  le nom de la scène
     * @param scene la scène à ajouter
     */
    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }

    /**
     * Supprime une scène de la collection par son nom.
     *
     * @param name le nom de la scène à supprimer
     */
    public void removeScene(String name) {
        scenes.remove(name);
    }

    /**
     * Vide la collection de scènes.
     */
    public void clearScenes() {
        scenes.clear();
    }

    public Map<String, Scene> getScenes() {
        return scenes;
    }

    /**
     * Récupère le nom d'une scène.
     * Utile pour le debug.
     *
     * @param scene la scène dont on veut le nom
     * @return le nom de la scène, ou null si la scène n'est pas dans la collection
     */
    public String getSceneName(Scene scene) {
        for (Map.Entry<String, Scene> entry : scenes.entrySet()) {
            if (entry.getValue().equals(scene)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Ajoute une feuille de style à une scène.
     *
     * @param scene la scène à laquelle ajouter la feuille de style
     */
    public void addStylesheet(Scene scene) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(GameConstants.CSS).toExternalForm());
    }

    // Les méthodes suivantes créent des scènes spécifiques et les ajoutent à la
    // collection
    public void createOptionsViewScene(Stage primaryStage) {
        OptionsView optionsView = new OptionsView(primaryStage);
        addStylesheet(optionsView.getScene());
        addScene("OptionsView", optionsView.getScene());
    }

    public void createStartMenuViewScene(Stage primaryStage) {
        StartMenuView startMenuView = new StartMenuView(primaryStage);
        addStylesheet(startMenuView.getScene());
        addScene("StartMenuView", startMenuView.getScene());
    }

    public void createSaveViewScene(Stage primaryStage) {
        SaveView saveView = new SaveView(primaryStage);
        addStylesheet(saveView.getScene());
        addScene("SaveView", saveView.getScene());
    }

    public void createTutoViewScene(Stage primaryStage) {
        TutoView tutoView = new TutoView(primaryStage);
        addStylesheet(tutoView.getScene());
        addScene("TutoView", tutoView.getScene());
    }

    /**
     * Change la scène actuelle du primaryStage par une autre scène de la
     * collection.
     *
     * @param primaryStage le primaryStage dont on veut changer la scène
     * @param name         le nom de la nouvelle scène
     */
    public void changeScene(Stage primaryStage, String name) {
        Scene newScene = getScene(name);
        Platform.runLater(() -> {
            // TODO: ajouter une transition jolie
            if (primaryStage.getScene() != null) { // Si une scène est déjà présente
                // Créer une transition de fondu pour la scène actuelle
                FadeTransition ft = new FadeTransition(Duration.millis(200), primaryStage.getScene().getRoot());
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished(event -> { // Quand la transition est terminée
                    primaryStage.setScene(newScene); // Changer la scène
                    // Créer une transition de fondu pour la nouvelle scène
                    FadeTransition ft2 = new FadeTransition(Duration.millis(200), newScene.getRoot());
                    ft2.setFromValue(0.0);
                    ft2.setToValue(1.0);
                    ft2.play(); // Jouer la transition
                });
                ft.play(); // Jouer la transition
            } else {
                primaryStage.setScene(newScene); // Si aucune scène n'est présente, simplement changer la scène
            }
        });
    }
}