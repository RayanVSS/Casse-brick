package gui.Menu.MenuControllers;

import config.Game;
import gui.App;
import gui.Menu.MenuViews.WinView;
import javafx.application.Platform;

public class WinController {
    private WinView view;

    public WinController(WinView view) {
        this.view = view;
        view.getBtnMenu().setOnAction(e -> {
            menu();
        });
        view.getBtnQuit().setOnAction(e -> {
            quit();
        });
        view.getBtnNext().setOnAction(e -> {
            next();
        });
    }

    public void menu() {
        App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    public void quit() {
        view.getPrimaryStage().close();
        Platform.exit();
        System.exit(0);
    }

    public void next() {
        Platform.runLater(() -> {
            Game.score=0;
            App.sceneManager.changeScene(view.getPrimaryStage(), "GameModeView");
    });
    }
}
