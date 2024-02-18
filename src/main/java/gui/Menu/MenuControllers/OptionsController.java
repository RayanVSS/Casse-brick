package gui.Menu.MenuControllers;

import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.StartMenuView;
import javafx.stage.Stage;
import utils.GameConstants;

public class OptionsController {
    private OptionsView view;

    public OptionsController(Stage p) {
        this.view = new OptionsView(p);
        this.view.getBtnBack().setOnAction(e -> back());
        this.view.getButtonfps().setOnAction(e -> fps());
        this.view.getButtonpath().setOnAction(e -> path());
        this.view.getButtonparticles().setOnAction(e -> particles());
        this.view.getVolumemusic().valueProperty().addListener((observable, oldValue, newValue) -> {
            GameConstants.MUSIC = newValue.intValue();
        });
        this.view.getVolumesound().valueProperty().addListener((observable, oldValue, newValue) -> {
            GameConstants.SOUND = newValue.intValue();
        });
        this.view.getButtonleft().setOnAction(e -> left());
        this.view.getButtonpower().setOnAction(e -> power());
        this.view.getButtonright().setOnAction(e -> right());
    }

    private void back() {
        view.getRoot().getChildren().clear();
        new StartMenuView(view.getPrimaryStage());
        new StartMenuController(view.getPrimaryStage());
    }
    private void fps() {
        if (view.getButtonfps().isSelected()) {
            view.getButtonfps().setText("ON");
        } else {
            view.getButtonfps().setText("OFF");
        }
        GameConstants.FPS = this.view.getButtonfps().isSelected();
    }
    private void path() {
        if (view.getButtonpath().isSelected()) {
            view.getButtonpath().setText("ON");
        } else {
            view.getButtonpath().setText("OFF");
        }
        GameConstants.PATH = this.view.getButtonpath().isSelected();
    }
    private void particles() {
        if (view.getButtonparticles().isSelected()) {
            view.getButtonparticles().setText("ON");
        } else {
            view.getButtonparticles().setText("OFF");
        }
        GameConstants.PARTICLES = this.view.getButtonparticles().isSelected();
    }
    private void left() {
        view.getButtonleft().setText("Appuyez sur une touche...");
        view.getButtonleft().setOnKeyPressed(event -> {
            GameConstants.LEFT = event.getCode();
            view.getButtonleft().setText(GameConstants.LEFT.getName());
            view.getButtonleft().setOnKeyPressed(null); // Désactive la liaison après la configuration
        });
    }
    private void power() {
        view.getButtonpower().setText("Appuyez sur une touche...");
        view.getButtonpower().setOnKeyPressed(event -> {
            GameConstants.SPACE = event.getCode();
            view.getButtonpower().setText(GameConstants.SPACE.getName());
            view.getButtonpower().setOnKeyPressed(null); // Désactive la liaison après la configuration
        });
    }

    private void right() {
        view.getButtonright().setText("Appuyez sur une touche...");
        view.getButtonright().setOnKeyPressed(event -> {
            GameConstants.RIGHT = event.getCode();
            view.getButtonright().setText(GameConstants.RIGHT.getName());
            view.getButtonright().setOnKeyPressed(null); // Désactive la liaison après la configuration
        });;
    }

}
