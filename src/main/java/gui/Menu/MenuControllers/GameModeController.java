package gui.Menu.MenuControllers;

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
        gameModeView.getBackButton().setOnAction(e -> back());
    }

    private void showStages() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameModeView.getPrimaryStage(), "Chapterview");
        });
    }

    private void showCustomizer() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameModeView.getPrimaryStage(), "GameCustomizerView");
        });
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameModeView.getPrimaryStage(), "StartMenuView");
        });
    }
}
