package gui.Menu.MenuControllers;

import config.Game;
import config.GameRules;
import config.Map;
import config.StageLevel;
import config.GameRules.BricksArrangement;
import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import entity.racket.ClassicRacket;
import entity.racket.MagnetRacket;
import entity.racket.Racket;
import entity.racket.YNotFixeRacket;
import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.GameCustomizerView;
import javafx.application.Platform;

public class GameCustomizerController {

    private GameCustomizerView gameCustomizerView;

    public GameCustomizerController(GameCustomizerView gameCustomizerView) {

        this.gameCustomizerView = gameCustomizerView;
        setOptionsBoxRight();
        setActionButtons();
    }

    private void setOptionsBoxRight() {
        gameCustomizerView.getRuleLimitedTime().getToggleButton()
                .setOnAction(e -> ruleLimitedTimeAction());

        gameCustomizerView.getRuleLimitedBounces().getToggleButton()
                .setOnAction(e -> ruleLimitedBouncesAction());

        gameCustomizerView.getRuleRandomSwitchBricks().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleRandomSwitchBricks().action());

        gameCustomizerView.getRuleColorRestricted().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleColorRestricted().action());

        gameCustomizerView.getRuleTransparent().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleTransparent().action());

        gameCustomizerView.getRuleUnbreakable().getToggleButton()
                .setOnAction(e -> gameCustomizerView.getRuleUnbreakable().action());
    }

    private void ruleLimitedTimeAction() {
        gameCustomizerView.getRuleLimitedTime().action();
        gameCustomizerView.getTimeLimit().getSlider()
                .setDisable(!gameCustomizerView.getRuleLimitedTime().getToggleButton().isSelected());
    }

    private void ruleLimitedBouncesAction() {
        gameCustomizerView.getRuleLimitedBounces().action();
        gameCustomizerView.getBouncesLimit().getSlider()
                .setDisable(!gameCustomizerView.getRuleLimitedBounces().getToggleButton().isSelected());
    }

    private void setActionButtons() {
        gameCustomizerView.getCreateGame().setOnAction(e -> createGame());
        gameCustomizerView.getBackButton().setOnAction(e -> back());
    }

    private void createGame() {

        StageLevel customLevel;
        Ball ball = getBallConfig();
        Racket racket = getRacketConfig();
        int columnsBricks = (int) gameCustomizerView.getMapWidht().getSlider().getValue();
        int rowsBricks = (int) gameCustomizerView.getMapHeight().getSlider().getValue();
        int life = (int) gameCustomizerView.getLife().getSlider().getValue();
        GameRules rules = getRulesConfig();

        Game customGame = new Game(ball, racket, life, rules, columnsBricks, rowsBricks);
        customLevel = new StageLevel(customGame, true);

        new GameView(gameCustomizerView.getPrimaryStage(), customLevel);
    }

    private void back() {
        Platform.runLater(() -> {
            App.sceneManager.changeScene(gameCustomizerView.getPrimaryStage(), "GameModeView");
        });
    }

    private Ball getBallConfig() {
        Ball ball;
        switch (gameCustomizerView.getBall().getComboBox().getValue()) {
            case "Classic":
                ball = new ClassicBall();
                break;

            case "Gravity":
                ball = new GravityBall();
                break;

            case "Hyper":
                ball = new HyperBall();
                break;

            case "Magnet":
                ball = new MagnetBall();
                break;

            default:
                ball = new ClassicBall();
                break;
        }
        ball.setRadius((int) gameCustomizerView.getBallSize().getSlider().getValue());
        ball.setSpeed(gameCustomizerView.getBallSpeed().getSlider().getValue());
        return ball;
    }

    private Racket getRacketConfig() {

        switch (gameCustomizerView.getRacket().getComboBox().getValue()) {
            case "Classic":
                return new ClassicRacket();

            case "Magnet":
                return new MagnetRacket();

            case "YNotFixe":
                return new YNotFixeRacket();

            default:
                return new ClassicRacket();
        }
    }

    private GameRules getRulesConfig() {
        GameRules rules = new GameRules(BricksArrangement.DEFAULT,
                gameCustomizerView.getRuleLimitedTime().getToggleButton().isSelected(),
                gameCustomizerView.getRuleLimitedBounces().getToggleButton().isSelected(),
                gameCustomizerView.getRuleRandomSwitchBricks().getToggleButton().isSelected(),
                gameCustomizerView.getRuleColorRestricted().getToggleButton().isSelected(),
                gameCustomizerView.getRuleTransparent().getToggleButton().isSelected(),
                gameCustomizerView.getRuleUnbreakable().getToggleButton().isSelected());
        if (gameCustomizerView.getRuleLimitedTime().getToggleButton().isSelected()) {
            rules.setRemainingTime((int) gameCustomizerView.getTimeLimit().getSlider().getValue());
        }
        if (gameCustomizerView.getRuleLimitedBounces().getToggleButton().isSelected()) {
            rules.setRemainingBounces((int) gameCustomizerView.getBouncesLimit().getSlider().getValue());
        }
        return rules;
    }
}
