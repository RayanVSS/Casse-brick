package gui.Menu.MenuControllers;

import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.StageSelectorView;
import javafx.application.Platform;
import javafx.scene.control.Button;
import save.PlayerData;

public class StageSelectorController {

    StageSelectorView stageSelectorView;

    public StageSelectorController(StageSelectorView stageSelectorView) {
        this.stageSelectorView = stageSelectorView;
        setButtonsAction();
    }

    private void setButtonsAction() {
        stageSelectorView.getBackButton().setOnAction(e -> back());
        for (int i = 0; i < stageSelectorView.getButtons().size(); i++) {
            int index = i; // Déclarer une variable locale finale pour stocker la valeur de i
            stageSelectorView.getButtons().get(i).setOnAction(e -> loadGame(index));
        }
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(stageSelectorView.getPrimaryStage(), "GameModeView");
        });
    }

    private void loadGame(int i) {
        if (PlayerData.stagesProgress.getStages()[i].canLoadGame()) {
            new GameView(stageSelectorView.getPrimaryStage(), PlayerData.stagesProgress.getStages()[i]);
        }
    }
}
