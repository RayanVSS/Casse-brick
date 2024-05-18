package gui.Menu.MenuControllers;

import gui.Menu.MenuViews.ChapterView;
import gui.App;
import javafx.application.Platform;

public class ChapterController {

    private ChapterView Chapterview;

    public ChapterController(ChapterView Chapterview) {
        this.Chapterview = Chapterview;
        setButtonsAction();
    }

    private void setButtonsAction() {
        Chapterview.getChapter1().setOnAction(e -> showChapter1());
        Chapterview.getChapter2().setOnAction(e -> showChapter2());
        Chapterview.getChapter3().setOnAction(e -> showChapter3());
        Chapterview.getChapter4().setOnAction(e -> showChapter4());
        Chapterview.getBackButton().setOnAction(e -> back());
    }

    private void showChapter1() {
        Platform.runLater(() -> {
            StageSelectorController.setChapter(0);
            App.menuManager.changeScene(Chapterview.getPrimaryStage(), "StageSelectorView");
        });
    }

    private void showChapter2() {
        Platform.runLater(() -> {
            StageSelectorController.setChapter(1);
            App.menuManager.changeScene(Chapterview.getPrimaryStage(), "StageSelectorView");
        });
    }

    private void showChapter3() {
        Platform.runLater(() -> {
            StageSelectorController.setChapter(2);
            App.menuManager.changeScene(Chapterview.getPrimaryStage(), "StageSelectorView");
        });
    }

    private void showChapter4() {
        Platform.runLater(() -> {
            StageSelectorController.setChapter(3);
            App.menuManager.changeScene(Chapterview.getPrimaryStage(), "StageSelectorView");
        });
    }


    private void back() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(Chapterview.getPrimaryStage(), "GameModeView");
        });
    }

}
