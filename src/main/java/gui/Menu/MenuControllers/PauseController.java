package gui.Menu.MenuControllers;

import gui.GameView;
import gui.Menu.MenuViews.PauseView;
import gui.Menu.MenuViews.StartMenuView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class PauseController {
    private Timer timer;
    private PauseView view;
    private StartMenuController startMenu;

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
        GameView game = new GameView(view.getPrimaryStage(), 1);
        game.animation();
    }

    private void quit() {
        view.getPrimaryStage().close();
        this.timer.cancel();
    }

    private void menu() {
        view.getChildren().clear();
        this.timer.cancel();
        new StartMenuController(view.getPrimaryStage(), new StartMenuView(view.getPrimaryStage()));
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
