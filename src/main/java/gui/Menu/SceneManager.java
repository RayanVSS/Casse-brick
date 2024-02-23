package gui.Menu;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import gui.GameView;
import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.SaveView;
import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.TutoView;

public class SceneManager {
    private Map<String, Scene> scenes = new HashMap<>();

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

    public void createOptionsViewScene(Stage primaryStage) {
        OptionsView optionsView = new OptionsView(primaryStage, this);
        addScene("OptionsView", optionsView.getScene());
    }

    public void createStartMenuViewScene(Stage primaryStage) {
        StartMenuView startMenuView = new StartMenuView(primaryStage, this);
        addScene("StartMenuView", startMenuView.getScene());
    }

    public void createSaveViewScene(Stage primaryStage) {
        SaveView saveView = new SaveView(primaryStage, this);
        addScene("SaveView", saveView.getScene());
    }

    public void createGameViewScene(Stage primaryStage) {
        GameView gameView = new GameView(primaryStage,1);
        addScene("GameView", gameView.getScene());
    }
    public void createTutoViewScene(Stage primaryStage) {
        TutoView tutoView = new TutoView(primaryStage, this);
        addScene("TutoView", tutoView.getScene());
    }
    public void changeScene(Stage primaryStage, String name) {
        primaryStage.setScene(getScene(name));
    }
}