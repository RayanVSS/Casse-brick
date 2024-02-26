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

public class SceneManager {
    private Map<String, Scene> scenes = new HashMap<>();

    public SceneManager() {
        System.out.println("SceneManager created");
    }

    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }

    public void removeScene(String name) {
        scenes.remove(name);
    }

    public void clearScenes() {

        scenes.clear();
    }

    public Map<String, Scene> getScenes() {
        return scenes;
    }

    public String getSceneName(Scene scene) {
        for (Map.Entry<String, Scene> entry : scenes.entrySet()) {
            if (entry.getValue().equals(scene)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void addStylesheet(Scene scene) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(GameConstants.CSS).toExternalForm());
    }

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

    public void changeScene(Stage primaryStage, String name) {

        Scene newScene = getScene(name);
        Platform.runLater(() -> {
            //TODO: ajouter une transition jolie
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
        System.out.println("Scene changed to " + name);
        System.out.println();
        System.out.println("Scenes in primaryStage:");
        for (Scene scene : this.getScenes().values()) {
            System.out.println(this.getSceneName(scene) + " " + scene.getStylesheets().toString());
        }
        System.out.println();

    }
}