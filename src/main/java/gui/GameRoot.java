package gui;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import javafx.scene.input.KeyCode;

import config.Game;
import entity.Boost;
import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BrickSet;
import gui.GraphicsFactory.ParticleGroup;
import gui.GraphicsFactory.RacketGraphics;
import gui.Menu.MenuViews.GameOverView;
import gui.Menu.MenuViews.PauseView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.GameConstants;
import utils.Key;

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

    public GameRoot(Game game, GameView gameView, Scene scene, Stage primaryStage) {
        this.game = game;
        this.scene = scene;
        this.gameRoot = this;
        this.gameView = gameView;
        this.primaryStage = primaryStage;
        this.graphBrickSet = new BrickSet(game.getMap().getBricks());
        this.graphBall = new BallGraphics(game.getBall());
        this.graphRacket = new RacketGraphics(game.getRacket());
        if (GameConstants.PARTICLES) {
            this.particleGroup = new ParticleGroup(root, game);
        }
        this.root.getChildren().add(graphBrickSet);
        this.root.getChildren().add(graphBall);
        this.root.getChildren().add(graphRacket);
        root.setPrefWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        root.getStyleClass().add("game-backgorund");
        //StackPane.setAlignment(root, Pos.CENTER_RIGHT);
    }

    public void update() {
        graphBall.update();
        graphRacket.update();
        if (GameConstants.PARTICLES) {
            particleGroup.update();
        }
        graphBrickSet.update();
        BoostUpdate();
    }

    public void animation(long deltaT) {
        BougePColision = key.isEmpty();
        key.handleInput(game);
        key.touchesR(scene, game);
        // prend les informations de la racquette pour la ball
        BougePColision = key.isEmpty();
        direction = key.getKeysPressed();
        game.update(deltaT);
        update();
        key.touchesM(scene, game);
        if (game.isLost()) {
            game.setLost(false);
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new GameOverView(primaryStage, gameView.getRoot()));
        }
        if (key.getKeysPressed().contains(KeyCode.ESCAPE)) {
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new PauseView(primaryStage, gameView.getRoot(), gameRoot.getRoot(),gameView.getAnimationTimer()));
        }
    }

    public void BoostUpdate() {
        Iterator<Boost> iterator = game.getBoosts().iterator();
        while (iterator.hasNext()) {
            Boost boost = iterator.next();
            if (boost.move(game.getRacket().CollisionRacket(boost.getC()), game.getRacket())) {
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

    public Pane getRoot() {
        return root;
    }

    public Game getGame() {
        return game;
    }

}
