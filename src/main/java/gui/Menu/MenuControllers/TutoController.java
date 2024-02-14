package gui.Menu.MenuControllers;

import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.TutoView;
import javafx.stage.Stage;

public class TutoController {
    private TutoView view;
    
    public TutoController(Stage p) {
        this.view = new TutoView(p);
        this.view.getBtnBack().setOnAction(e -> back());
    }
    private void back() {
        StartMenuView  startMenuView = new StartMenuView(view.getPrimaryStage());
        StartMenuController startMenuC = new StartMenuController(view.getPrimaryStage());    
    }
}
