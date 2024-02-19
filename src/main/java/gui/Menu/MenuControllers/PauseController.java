package gui.Menu.MenuControllers;

import gui.GameView;
import gui.Menu.MenuViews.PauseView;
import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.StartMenuView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class PauseController {
    private PauseView view;

    public PauseController(Stage p, PauseView view) {
        this.view = view;
        this.view.getBtnReplay().setOnAction(e -> replay());
        this.view.getBtnQuit().setOnAction(e -> quit());
        this.view.getBtnMenu().setOnAction(e -> menu());
        this.view.getBtnResume().setOnAction(e -> resume());
    }

    private void replay() {
        view.getChildren().clear();
        GameView game = new GameView(view.getPrimaryStage(), 1);
        game.animation();
    }

    private void quit() {
        view.getPrimaryStage().close();
    }

    private void menu() {
        view.getChildren().clear();
         new StartMenuView(view.getPrimaryStage());
    }

    private void resume() {
        view.getChildren().clear();
        Timer timer = new Timer();
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
