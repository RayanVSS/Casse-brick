package gui.Menu;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import gui.Menu.MenuViews.GameCustomizerView;
import gui.Menu.MenuViews.GameModeView;
import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.SaveView;
import gui.Menu.MenuViews.StageSelectorView;
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
        scene.getStylesheets().add(getClass().getResource(GameConstants.CSS.getPath()).toExternalForm());
    }

    // Charge toutes les scènes du jeu
    public void preCreateAllView(Stage primaryStage) {
        createStartMenuViewScene(primaryStage);
        createOptionsViewScene(primaryStage);
        createSaveViewScene(primaryStage);
        createTutoViewScene(primaryStage);
        createGameModeViewScene(primaryStage);
        createStageSelectorViewScene(primaryStage);
        createGameCustomizerViewScene(primaryStage);
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

    public void createGameModeViewScene(Stage primaryStage) {
        GameModeView gameModeView = new GameModeView(primaryStage);
        addStylesheet(gameModeView.getScene());
        addScene("GameModeView", gameModeView.getScene());
    }

    public void createStageSelectorViewScene(Stage primaryStage) {
        StageSelectorView stageSelectorView = new StageSelectorView(primaryStage);
        addStylesheet(stageSelectorView.getScene());
        addScene("StageSelectorView", stageSelectorView.getScene());
    }

    public void createGameCustomizerViewScene(Stage primaryStage) {
        GameCustomizerView gameCustomizerView = new GameCustomizerView(primaryStage);
        addStylesheet(gameCustomizerView.getScene());
        addScene("GameCustomizerView", gameCustomizerView.getScene());
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
        primaryStage.setScene(newScene);
        // Platform.runLater(() -> {
        //     if (primaryStage.getScene() != null) {
        //         // Créer une transition de translation pour la scène actuelle
        //         TranslateTransition tt = new TranslateTransition(Duration.millis(200), primaryStage.getScene().getRoot());
        //         tt.setFromX(0);
        //         tt.setToX(primaryStage.getWidth());
        //         tt.setOnFinished(event -> {
        //             primaryStage.setScene(newScene);
        //             // Créer une transition de translation pour la nouvelle scène
        //             TranslateTransition tt2 = new TranslateTransition(Duration.millis(200), newScene.getRoot());
        //             tt2.setFromX(primaryStage.getWidth());
        //             tt2.setToX(0);
        //             tt2.play();
        //         });
        //         tt.play();
        //     } else {
        //         primaryStage.setScene(newScene);
        //     }
        // });
    }
}