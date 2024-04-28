package gui.Menu.MenuControllers;

import config.Game;
import config.GameRules;
import config.StageLevel;
import entity.ball.ClassicBall;
import entity.racket.ClassicRacket;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.InfinityModeView;
import javafx.application.Platform;
import utils.GameConstants;

public class InfinityModeController {
    private InfinityModeView infinityModeView;

    public InfinityModeController(InfinityModeView inifinityModeView) {
        this.infinityModeView = inifinityModeView;
        this.infinityModeView.getBackButton().setOnAction(e -> back());
        this.infinityModeView.getPlayButton().setOnAction(e -> play());
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(infinityModeView.getPrimaryStage(), "GameModeView");
        });
    }

    public void play() {
        ClassicBall ball = new ClassicBall();
        ClassicRacket racket = new ClassicRacket();
        GameRules rules = GameConstants.INFINITE_MODE;
        Game game = new Game(ball, racket, rules, true);
        StageLevel level = new StageLevel(game);
        new GameView(infinityModeView.getPrimaryStage(), level);
    }

}
