package gui.Menu.MenuControllers;

import config.Game;
import config.GameRules;
import config.Map;
import config.StageLevel;
import entity.ball.Ball;
import entity.racket.Racket;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.GameCustomizerView;
import javafx.application.Platform;

public class GameCustomizerController {

    private GameCustomizerView gameCustomizerView;

    public GameCustomizerController(GameCustomizerView gameCustomizerView) {

        this.gameCustomizerView = gameCustomizerView;
        setOptionsBoxLeft();
        setActionButtons();
    }

    private void setOptionsBoxLeft() {
        gameCustomizerView.getRuleLimitedTime().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleLimitedTime().action());
        gameCustomizerView.getRuleLimitedBounces().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleLimitedBounces().action());
        gameCustomizerView.getRuleRandomSwitchBricks().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleRandomSwitchBricks().action());
        gameCustomizerView.getRuleColorRestricted().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleColorRestricted().action());
        gameCustomizerView.getRuleTransparent().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleTransparent().action());
        gameCustomizerView.getRuleUnbreakable().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleUnbreakable().action());
    }

    private void setActionButtons() {
        gameCustomizerView.getCreateGame().setOnAction(e -> createGame());
        gameCustomizerView.getBackButton().setOnAction(e -> back());
    }

    private void createGame() {

        // StageLevel customLevel;
        // Ball ball = getBallConfig();
        // Racket racket = getRacketConfig();
        // int mapWidht = (int) gameCustomizerView.getMapWidht().getValue();
        // int mapHeight = (int) gameCustomizerView.getMapHeight().getValue();
        // int life = (int) gameCustomizerView.getLife().getValue();
        // GameRules rules = getRulesConfig();

        // Game customGame = new Game(ball, racket, mapWidht, mapHeight, life, rules);
        // customLevel = new StageLevel(customGame, true);

        // new GameView(gameCustomizerView.getPrimaryStage(), customLevel);
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameCustomizerView.getPrimaryStage(), "GameModeView");
        });
    }

    private Ball getBallConfig() {
        return null;
    }

    private Racket getRacketConfig() {
        return null;
    }

    private GameRules getRulesConfig() {
        // boolean 
        return null;
    }
}
