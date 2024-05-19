package gui.Menu.MenuControllers;

import org.checkerframework.checker.units.qual.g;

import config.Game;
import config.GameRules;
import config.StageLevel;
import entity.ball.ClassicBall;
import entity.racket.ClassicRacket;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.GameModeView;
import javafx.application.Platform;
import utils.GameConstants;
import utils.Sound.ClickSound;

public class GameModeController {

    private GameModeView gameModeView;
    private ClickSound click = App.clickSoundPlayer;

    public GameModeController(GameModeView gameModeView) {
        this.gameModeView = gameModeView;
        setButtonsAction();
    }

    private void setButtonsAction() {
        gameModeView.getStagesButton().setOnAction(e -> {
            click.play();
            showStages();
        });
        gameModeView.getCustomGameButton().setOnAction(e -> {
            click.play();
            showCustomizer();
        });
        gameModeView.getBackButton().setOnAction(e -> {
            click.play();
            back();
        });
        gameModeView.getInfinityButton().setOnAction(e -> {
            click.play();
            playInfinite();
        });
    }

    private void showStages() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(gameModeView.getPrimaryStage(), "Chapterview");
        });
    }

    private void showCustomizer() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(gameModeView.getPrimaryStage(), "GameCustomizerView");
        });
    }

    public void playInfinite() {
        ClassicBall ball = new ClassicBall();
        ClassicRacket racket = new ClassicRacket();
        GameRules rules = GameConstants.INFINITE_MODE;
        Game game = new Game(ball, racket, rules);
        StageLevel level = new StageLevel(game);
        new GameView(gameModeView.getPrimaryStage(), level);
    }

    private void back() {
        Platform.runLater(() -> {
            App.menuManager.changeScene(gameModeView.getPrimaryStage(), "StartMenuView");
        });
    }
}
