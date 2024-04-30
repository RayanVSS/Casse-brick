package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.BoutiqueView;
import utils.Sound.ClickSound;

public class BoutiqueController {
    
    private BoutiqueView view;
    private ClickSound  click = App.clickSoundPlayer;

    public BoutiqueController(BoutiqueView view) {
        this.view = view;
        this.view.getBtnBack().setOnAction(e -> {
            click.play();
            back();
        });
    }

    private void back() {
        App.sceneManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }
}
