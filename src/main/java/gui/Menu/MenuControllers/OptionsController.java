package gui.Menu.MenuControllers;

import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.StartMenuView;
import javafx.stage.Stage;

public class OptionsController {
    private OptionsView view;

    public OptionsController(Stage p) {
        this.view = new OptionsView(p);
        this.view.getBtnBack().setOnAction(e -> back());
    }

    private void back() {
        view.getRoot().getChildren().clear();
        StartMenuView startMenuV = new StartMenuView(view.getPrimaryStage());
        StartMenuController startMenuC = new StartMenuController(view.getPrimaryStage());
    }
}
