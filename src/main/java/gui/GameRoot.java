package gui;

import static physics.entity.Racket.AddIntensityBall;
//pour les boosts
import static physics.entity.Racket.StopBall;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import config.Game;
import config.StageLevel;
import entity.Boost;
import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BrickSet;
import gui.GraphicsFactory.ParticleGroup;
import gui.GraphicsFactory.RacketGraphics;
import gui.Menu.MenuViews.GameOverView;
import gui.Menu.MenuViews.PauseView;
import gui.Menu.MenuViews.LevelUpView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.GameConstants;
import utils.Key;;

public class GameRoot {
    private Pane root = new Pane();
    private Game game;
    private BrickSet graphBrickSet;
    private BallGraphics graphBall;
    private RacketGraphics graphRacket;
    public static boolean BougePColision;
    public static Set<KeyCode> direction = new HashSet<>();
    private ParticleGroup particleGroup;
    private Key key = new Key();
    private Scene scene;
    private Stage primaryStage;
    private GameRoot gameRoot;
    private GameView gameView;
    private StageLevel level;

    public GameRoot(StageLevel level, GameView gameView, Scene scene, Stage primaryStage) {
        this.level = level;
        this.game = level.getGame();
        this.scene = scene;
        this.gameRoot = this;
        this.gameView = gameView;
        this.primaryStage = primaryStage;
        this.graphBrickSet = new BrickSet(game.getMap().getBricks());
        this.graphBall = new BallGraphics(game.getBall());
        // rectangle rond losange triangle
        this.graphRacket = new RacketGraphics(game.getRacket(), game.getRacket().getShapeType());
        if (GameConstants.PARTICLES) {
            this.particleGroup = new ParticleGroup(root, game);
        }
        this.root.getChildren().add(graphBrickSet);
        this.root.getChildren().add(graphBall);
        this.root.getChildren().add(graphRacket.getShape());
        root.setPrefWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        root.getStyleClass().add("game-backgorund");
        game.start();
    }

    public void update(long deltaT) {
        BoostAction();
        graphBall.update();
        root.getChildren().remove(graphRacket.getShape());
        graphRacket.update();
        root.getChildren().add(graphRacket.getShape()); // Ajoute la forme de la raquette mise à jour
        if (GameConstants.PARTICLES) {
            particleGroup.update();
        }
        graphBrickSet.update();
        BoostUpdate();
        BougePColision = key.isEmpty();
        key.handleInput(game);
        key.touchesR(scene, game);
        // prend les informations de la racquette pour la ball
        BougePColision = key.isEmpty();
        direction = key.getKeysPressed();
        game.update(deltaT);
        key.touchesM(scene, game);
        if (game.isLost()) {
            App.gameOverS.play();
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new GameOverView(primaryStage, gameView));
            level.lostAction();
        }
        if (key.getKeysPressed().contains(KeyCode.ESCAPE)) {
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new PauseView(primaryStage, gameView.getRoot(), gameRoot.getRoot(),
                    gameView.getAnimationTimer(), level));
        }
        if (level.getGame().isWin()) {
            level.resetGame();
            gameView.getRoot().getChildren().add(new LevelUpView(primaryStage, gameView, level));
            gameView.animationStop();
        }
    }

    public void BoostUpdate() {
        Iterator<Boost> iterator = game.getBoosts().iterator();
        while (iterator.hasNext()) {
            Boost boost = iterator.next();
            if (boost.move(game.getRacket().CollisionRacket(boost.getC(), game.getRacket().getShapeType()),
                    game.getRacket())) {
                switch (boost.getType()) {
                    case "vitesseP":
                    case "largeurP":
                    case "zhonya":
                        App.bonusSound.play();
                        break;
                    case "intensityBall":
                    case "freeze":
                    case "vitesseM":
                    case "largeurM":
                        App.malusSound.play();
                        break;
                    default:
                        break;
                }
                root.getChildren().remove(boost);
                iterator.remove();
            } else {
                if (!root.getChildren().contains(boost)) {
                    root.getChildren().add(boost);
                }
                if (boost.getY() > GameConstants.DEFAULT_WINDOW_HEIGHT) {
                    root.getChildren().remove(boost);
                    iterator.remove();
                }
            }
        }
    }

    // gere les modifiactaion fait par les boosts
    public void BoostAction() {
        if (StopBall) {
            game.getBall().setSpeed(0);
            game.getBall().setDirection(new physics.geometry.Vector(0, 1));
        } else if (AddIntensityBall) {
            game.getBall().setSpeed(GameConstants.DEFAULT_BALL_SPEED * GameConstants.BOOST_INTENSITY_BALL);
        } else {
            game.getBall().setSpeed(GameConstants.DEFAULT_BALL_SPEED);
        }
    }

    public Pane getRoot() {
        return root;
    }

    public Game getGame() {
        return game;
    }

}
