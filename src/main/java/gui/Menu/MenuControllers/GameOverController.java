package gui.Menu.MenuControllers;

import gui.GameView;
import gui.Menu.MenuViews.GameOverView;
import javafx.stage.Stage;

public class GameOverController {
    private GameOverView view;

    public GameOverController(Stage p, GameOverView gameView) {
        //this.view = new GameOverView(p);
        this.view=gameView;
        this.view.getBtnReplay().setOnAction(e -> replay());
        this.view.getBtnQuit().setOnAction(e -> quit());
        this.view.getBtnMenu().setOnAction(e -> menu());
    }

    private void replay() {
        view.getRoot().getChildren().clear();
        GameView game = new GameView(view.getPrimaryStage(), 1);
        game.animation();
    }

    private void quit() {
        view.getPrimaryStage().close();
    }

    private void menu() {
        view.getRoot().getChildren().clear();
        new StartMenuController(view.getPrimaryStage());
    }
}
