package gui.Menu.MenuControllers;

import java.util.Timer;
import java.util.TimerTask;

import config.Game;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.PauseView;
import javafx.application.Platform;
import javafx.stage.Stage;

public class PauseController {
    private Timer timer;
    private PauseView view;

    public PauseController(Stage p, PauseView view) {
        this.timer = new Timer();
        this.view = view;
        this.view.getBtnReplay().setOnAction(e -> replay());
        this.view.getBtnQuit().setOnAction(e -> quit());
        this.view.getBtnMenu().setOnAction(e -> menu());
        this.view.getBtnResume().setOnAction(e -> resume());
    }

    private void replay() {
        view.getChildren().clear();
        this.timer.cancel();
        Game.score = 0;
        new GameView(view.getPrimaryStage(), 1);
    }

    private void quit() {
        this.timer.cancel();
        view.getPrimaryStage().close();
        Platform.exit();
        System.exit(0);
    }

    private void menu() {
        view.getChildren().clear();
        this.timer.cancel();
        App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    private void resume() {
        view.getRoot().getChildren().remove(view);
        TimerTask task = new TimerTask() {
            public void run() {
                view.getAnimationTimer().start();
            }
        };
        timer.schedule(task, 1000);
    }

    public PauseView getView() {
        return view;
    }

}