package gui.Menu.MenuControllers;

import config.Game;
import config.GameRules;
import config.Map;
import config.StageLevel;
import physics.entity.Ball;
import physics.entity.Racket;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.GameCustomizerView;
import javafx.application.Platform;

public class GameCustomizerController {

    private GameCustomizerView gameCustomizerView;

    public GameCustomizerController(GameCustomizerView gameCustomizerView) {

        this.gameCustomizerView = gameCustomizerView;

        gameCustomizerView.getBackButton().setOnAction(e -> back());
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameCustomizerView.getPrimaryStage(), "GameModeView");
        });
    }

    private void createGame() {
        StageLevel customLevel;
        Ball ball = getBallConfig();
        Racket racket = getRacketConfig();
        int mapWidht = (int) gameCustomizerView.getMapWidht().getValue();
        int mapHeight = (int) gameCustomizerView.getMapHeight().getValue();
        int life = (int) gameCustomizerView.getLife().getValue();
        GameRules rules = getRulesConfig();

        Game customGame = new Game(ball, racket, mapWidht, mapHeight, life, rules);
        customLevel = new StageLevel(customGame, true);

        new GameView(gameCustomizerView.getPrimaryStage(), customLevel);
    }

    private Ball getBallConfig() {
        return null;
    }

    private Racket getRacketConfig() {
        return null;
    }

    private Map getMapConfig() {
        return null;
    }

    private GameRules getRulesConfig() {
        return null;
    }
}
