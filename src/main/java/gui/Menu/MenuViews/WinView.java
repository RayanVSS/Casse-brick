package gui.Menu.MenuViews;

import gui.GameView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.GameOverController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.GameConstants;

public class WinView extends Pane implements Menu {

    private Stage primaryStage;
    private GameView game;
    private Button btnMenu;
    private Button btnQuit;
    private Button btnNext;
    private Label win;
    

    public WinView(Stage p,GameView game ) {
        this.primaryStage = p;
        this.game = game;
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
        new WinView(p, game);

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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    
}
