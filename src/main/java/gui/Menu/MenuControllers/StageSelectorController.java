package gui.Menu.MenuControllers;

import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.StageSelectorView;
import javafx.application.Platform;
import save.PlayerData;
import utils.Sound.ClickSound;

public class StageSelectorController {

    StageSelectorView stageSelectorView;
    private static int chapter;
    private ClickSound click = App.clickSoundPlayer;

    public StageSelectorController(StageSelectorView stageSelectorView) {
        this.stageSelectorView = stageSelectorView;
        setButtonsAction();

    }

    private void setButtonsAction() {
        stageSelectorView.getBackButton().setOnAction(e -> {
            click.play();
            back();
        });
        for (int i = 0; i < stageSelectorView.getButtons().size(); i++) {
            int index = i; // Déclarer une variable locale finale pour stocker la valeur de i
            stageSelectorView.getButtons().get(i).setOnAction(e -> loadGame(index));
        }
    }

    private void back() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(stageSelectorView.getPrimaryStage(), "Chapterview");
        });
    }

    private void loadGame(int i) {
        if (PlayerData.stagesProgress == null) {
            PlayerData.initPlayerData();
        }
        // System.err.println(i + (chapter * 9));
        // System.err.println(PlayerData.stagesProgress.getStages().length);
        if (PlayerData.stagesProgress.getStages()[i + (chapter * 9)].canLoadGame()) {
            new GameView(stageSelectorView.getPrimaryStage(), PlayerData.stagesProgress.getStages()[i + (chapter * 9)]);
        }
    }

    // Getters et Setters
    public static void setChapter(int chapter) {
        StageSelectorController.chapter = chapter;
    }

    public static int getChapter() {
        return chapter;
    }
}
