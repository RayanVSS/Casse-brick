package gui.Menu.MenuControllers;

import gui.Menu.MenuViews.Chapterview;
import gui.App;
import javafx.application.Platform;

public class ChapterController {

    
    private Chapterview Chapterview;

    public ChapterController(Chapterview Chapterview) {
        this.Chapterview = Chapterview;
        setButtonsAction();
    }

    private void setButtonsAction() {
        Chapterview.getChapter1().setOnAction(e -> showChapter1());
        Chapterview.getChapter2().setOnAction(e -> showChapter2());
        Chapterview.getChapter3().setOnAction(e -> showChapter3());
        Chapterview.getBackButton().setOnAction(e -> back());
    }

    private void showChapter1() {
        Platform.runLater(() -> {
            StageSelectorController.setChapter(0);
            App.sceneManager.changeScene(Chapterview.getPrimaryStage(), "StageSelectorView");
        });
    }

    private void showChapter2() {
        Platform.runLater(() -> {
            StageSelectorController.setChapter(1);
            App.sceneManager.changeScene(Chapterview.getPrimaryStage(), "StageSelectorView");
        });
    }

    private void showChapter3() {
        Platform.runLater(() -> {
            StageSelectorController.setChapter(2);
            App.sceneManager.changeScene(Chapterview.getPrimaryStage(), "StageSelectorView");
        });
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(Chapterview.getPrimaryStage(), "GameModeView");
        });
    }

}
