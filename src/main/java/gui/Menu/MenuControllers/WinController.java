package gui.Menu.MenuControllers;

import config.Game;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.WinView;
import javafx.application.Platform;
import save.PlayerData;
import utils.Sound.ClickSound;

public class WinController {
    private WinView view;
    private ClickSound click = App.clickSoundPlayer;

    public WinController(WinView view) {
        this.view = view;
        view.getBtnMenu().setOnAction(e -> {
            click.play();
            menu();
        });
        view.getBtnQuit().setOnAction(e -> {
            click.play();
            quit();
        });
        view.getBtnNext().setOnAction(e -> {
            click.play();
            next();
        });
    }

    private void quit() {
        view.getPrimaryStage().close();
        Platform.exit();
        System.exit(0);
    }

    public void menu() {
        App.menuManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    public void next() {
        Platform.runLater(() -> {
            int nextStageIndex = view.getLevel().getDifficulty() + 1;
            if (nextStageIndex < (PlayerData.stagesProgress.getStages().length - 1)
                    && PlayerData.stagesProgress.getStages()[nextStageIndex].canLoadGame()) {
                new GameView(view.getPrimaryStage(), PlayerData.stagesProgress.getStages()[nextStageIndex]);
            } else {
                // Boite erreur, tous les lvl finis
            }
        });
    }
}
