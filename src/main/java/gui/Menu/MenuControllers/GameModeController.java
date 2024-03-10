package gui.Menu.MenuControllers;

import org.checkerframework.checker.units.qual.g;

import gui.App;
import gui.Menu.MenuViews.GameModeView;
import javafx.application.Platform;

public class GameModeController {

    private GameModeView gameModeView;

    public GameModeController(GameModeView gameModeView) {
        this.gameModeView = gameModeView;
        setButtonsAction();
    }

    private void setButtonsAction() {
        gameModeView.getStagesButton().setOnAction(e -> showStages());
        gameModeView.getCustomGameButton().setOnAction(e -> showCustomizer());
        gameModeView.getInfinityButton().setOnAction(e -> showInfinityMode());
        gameModeView.getBackButton().setOnAction(e -> back());
    }

    private void showStages() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameModeView.getPrimaryStage(), "StageSelectorView");
        });
    }

    private void showCustomizer() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameModeView.getPrimaryStage(), "GameCustomizerView");
        });
    }

    private void showInfinityMode() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameModeView.getPrimaryStage(), "InfinityModeView");
        });
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameModeView.getPrimaryStage(), "StartMenuView");
        });
    }
}
