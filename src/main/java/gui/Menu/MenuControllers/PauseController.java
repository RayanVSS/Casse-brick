package gui.Menu.MenuControllers;

import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.PauseView;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class PauseController {
    private Timer timer;
    private PauseView view;
    boolean isTimerRunning;

    public PauseController(Stage p, PauseView view) {
        this.timer = new Timer();
        this.view = view;
        isTimerRunning = true;
        this.view.getBtnReplay().setOnAction(e -> replay());
        this.view.getBtnQuit().setOnAction(e -> quit());
        this.view.getBtnMenu().setOnAction(e -> menu());
        this.view.getBtnResume().setOnAction(e -> resume());

    }

    private void replay() {
        view.getChildren().clear();
        this.timer.cancel();
        isTimerRunning = false;
        System.out.println("Timer state Replay: " + (isTimerRunning ? "Running" : "Not running"));
        new GameView(view.getPrimaryStage(), 1);
    }

    private void quit() {
        this.timer.cancel();
        view.getPrimaryStage().close();
        isTimerRunning = false;
        System.out.println("Timer state QUIT: " + (isTimerRunning ? "Running" : "Not running"));
    }

    private void menu() {
        view.getChildren().clear();
        this.timer.cancel();
        isTimerRunning = false;
        System.out.println("Timer state MENU: " + (isTimerRunning ? "Running" : "Not running"));
        App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    private void resume() {
        view.getRoot().getChildren().remove(view);
        TimerTask task = new TimerTask() {
            public void run() {
                isTimerRunning = true;
                System.out.println("Timer state CReation timer: " + (isTimerRunning ? "Running" : "Not running"));
                view.getAnimationTimer().start();
                
            }
        };
        timer.schedule(task, 1000);
    }

    public PauseView getView() {
        return view;
    }

}
