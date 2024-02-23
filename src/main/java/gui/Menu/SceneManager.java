package gui.Menu;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import gui.GameView;
import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.SaveView;
import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.TutoView;
import javafx.util.Duration;

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

    public void createOptionsViewScene(Stage primaryStage) {
        OptionsView optionsView = new OptionsView(primaryStage, this);
        CompletableFuture.runAsync(() -> {
            optionsView.getScene().getStylesheets().add(getClass().getResource("/styles/blue.css").toExternalForm());
        });
        addScene("OptionsView", optionsView.getScene());
        System.out.println("OptionsView created in SceneManager");
    }

    public void createStartMenuViewScene(Stage primaryStage) {
        StartMenuView startMenuView = new StartMenuView(primaryStage, this);
        Platform.runLater(() -> {
            startMenuView.getScene().getStylesheets().add(getClass().getResource("/styles/blue.css").toExternalForm());
            System.out.println("StartMenuView css created in CompletableFuture");
        });
        addScene("StartMenuView", startMenuView.getScene());
        System.out.println("StartMenuView created");
    }

    public void createSaveViewScene(Stage primaryStage) {
        SaveView saveView = new SaveView(primaryStage, this);
        CompletableFuture.runAsync(() -> {
            saveView.getScene().getStylesheets().add(getClass().getResource("/styles/blue.css").toExternalForm());
        });
        addScene("SaveView", saveView.getScene());
        System.out.println("SaveView created");
    }

    // public void createGameViewScene(Stage primaryStage) {
    //     GameView gameView = new GameView(primaryStage, 1, this);

    //     CompletableFuture.runAsync(() -> {
    //         gameView.getScene().getStylesheets().add(getClass().getResource("/styles/blue.css").toExternalForm());
    //     });
    //     //gameView.animation();
    //     addScene("GameView", gameView.getScene());
    //     System.out.println("GameView created");
    // }

    public void createTutoViewScene(Stage primaryStage) {
        TutoView tutoView = new TutoView(primaryStage, this);
        CompletableFuture.runAsync(() -> {
            tutoView.getScene().getStylesheets().add(getClass().getResource("/styles/blue.css").toExternalForm());
        });
        addScene("TutoView", tutoView.getScene());
        System.out.println("TutoView created");
    }

    public void changeScene(Stage primaryStage, String name) {
        Scene newScene = getScene(name);
        Platform.runLater(() -> {
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
            // Platform.runLater(() -> {
            // primaryStage.setScene(newScene);
            // });
        });
        System.out.println("Scene changed to " + name);
        System.out.println();
        System.out.println("Scenes in primaryStage:");
        for (Scene scene : this.getScenes().values()) {
            System.out.println(scene + " " + this.getSceneName(scene) + " added to primaryStage");
        }
        System.out.println();

    }
}