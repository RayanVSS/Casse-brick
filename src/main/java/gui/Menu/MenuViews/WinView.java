package gui.Menu.MenuViews;

import config.StageLevel;
import gui.App;
import gui.GameView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.WinController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class WinView extends VBox implements Menu {

    private Stage primaryStage;
    private GameView game;
    private StageLevel level;
    private Button btnMenu;
    private Button btnQuit;
    private Button btnNext;
    private Label win;

    public WinView(Stage p, GameView game, StageLevel level) {
        App.levelUp.play();
        this.primaryStage = p;
        this.game = game;
        this.level = level;
        btnMenu = createButton("Menu", 0, 0);
        btnQuit = createButton("Quitter", 0, 0);
        btnNext = createButton("Niveau suivant", 0, 0);
        win = createLabel("Gagné!!", 0, 0);

        win.getStyleClass().add("title-game-over-style");
        getChildren().addAll(win, btnNext, btnMenu, btnQuit);

        setLayoutX(game.getRoot().getLayoutX());
        setLayoutY(game.getRoot().getLayoutY());
        setPrefWidth(game.getRoot().getWidth());
        setPrefHeight(game.getRoot().getHeight());
        getStyleClass().add("root-game-over");
        getStylesheets().add(GameConstants.CSS.getPath());

        if (level.getDifficulty() < GameConstants.STAGES_QTY - 1) {
            level.winAction();
            new WinController(this);
        } else {
            finDuJeu();
        }
        new WinController(this);
    }

    public void finDuJeu() {
        this.getChildren().clear();
        Label fiin = createLabel("Vous avez terminé le jeu.", 0, 0);
        Label fiin2 = createLabel("Merci d'avoir joué!", 0, 0);
        fiin.getStyleClass().add("fin-label");
        fiin2.getStyleClass().add("fin-label");
        getChildren().addAll(fiin, fiin2, btnMenu, btnQuit);
    }

    public Button getBtnMenu() {
        return btnMenu;
    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public Label getWin() {
        return win;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public GameView getGame() {
        return game;
    }

    public StageLevel getLevel() {
        return level;
    }

}
