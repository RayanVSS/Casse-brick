package gui.Menu.MenuControllers;

import gui.GameView;
import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.StartMenuView;
import javafx.stage.Stage;

public class StartMenuController {
    private StartMenuView view;

    public StartMenuController(Stage p) {
        this.view = new StartMenuView(p);
        this.view.getBtnPlay().setOnAction(e -> play());
        this.view.getBtnOptions().setOnAction(e -> options());
        this.view.getBtnQuit().setOnAction(e -> quit());
    }

    private void play() {
        view.getRoot().getChildren().clear();
        GameView game = new GameView(view.getPrimaryStage(), 1);
        game.getRoot().setStyle("-fx-background-color: #E0ECF5;");
        game.animation();
    }

    private void options() {
        view.getRoot().getChildren().clear();
        OptionsView options = new OptionsView(view.getPrimaryStage());
        OptionsController optionsController = new OptionsController(view.getPrimaryStage());
        
    }
    private void quit() {
        view.getPrimaryStage().close();
    }
}
