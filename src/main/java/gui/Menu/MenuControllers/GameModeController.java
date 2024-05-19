package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.GameModeView;
import javafx.application.Platform;
import utils.Sound.ClickSound;

public class GameModeController {

    private GameModeView gameModeView;
    private ClickSound click = App.clickSoundPlayer;

    public GameModeController(GameModeView gameModeView) {
        this.gameModeView = gameModeView;
        setButtonsAction();
    }

    private void setButtonsAction() {
        gameModeView.getStagesButton().setOnAction(e -> {
            click.play();
            showStages();
        });
        gameModeView.getCustomGameButton().setOnAction(e -> {
            click.play();
            showCustomizer();
        });
        gameModeView.getBackButton().setOnAction(e -> {
            click.play();
            back();
        });
        gameModeView.getInfinityButton().setOnAction(e -> showInfinityMode());
    }

    private void showStages() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(gameModeView.getPrimaryStage(), "Chapterview");
        });
    }

    private void showCustomizer() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(gameModeView.getPrimaryStage(), "GameCustomizerView");
        });
    }

    private void showInfinityMode() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(gameModeView.getPrimaryStage(), "InfinityModeView");
        });
    }

    private void back() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(gameModeView.getPrimaryStage(), "StartMenuView");
        });
    }
}
