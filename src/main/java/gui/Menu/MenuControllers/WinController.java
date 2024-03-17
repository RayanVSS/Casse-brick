package gui.Menu.MenuControllers;

import config.Game;
import config.StageLevel;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.WinView;
import javafx.application.Platform;
import save.PlayerData;

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

    private void quit() {
        view.getPrimaryStage().close();
        Platform.exit();
        System.exit(0);
    }

    public void menu() {
        App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    public void next() {
        Platform.runLater(() -> {
            Game.score = 0;
            int nextStageIndex = view.getLevel().getDifficulty() ;
            if (nextStageIndex < (PlayerData.stagesProgress.getStages().length-1) && PlayerData.stagesProgress.getStages()[nextStageIndex].canLoadGame()) {
                new GameView(view.getPrimaryStage(), PlayerData.stagesProgress.getStages()[nextStageIndex]);
            }
        });
    }
}
