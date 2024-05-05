package gui;

import static physics.entity.Racket.AddIntensityBall;
//pour les boosts
import static physics.entity.Racket.StopBall;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import config.Game;
// import config.Map;
import config.StageLevel;
import entity.Boost;
import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BricksGraphics;
import gui.GraphicsFactory.EntityGraphics;
// import gui.GraphicsFactory.BrickSet;
import gui.GraphicsFactory.ParticleGroup;
import gui.GraphicsFactory.RacketGraphics;
import gui.Menu.MenuViews.GameOverView;
import gui.Menu.MenuViews.PauseView;
import gui.Menu.MenuViews.WinView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.entity.Entity;
import utils.GameConstants;
import utils.Key;;

public class GameRoot {
    private Pane root = new Pane();
    private Game game;
    // private BrickSet graphBrickSet;

    private ArrayList<Brick> bricks;
    private ArrayList<Ball> balls;
    private Map<Entity, EntityGraphics> entities;
    
    // private BallGraphics graphBall;
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

        this.bricks = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.entities = new HashMap<>();
        this.addBall(game.getBall());
        for (int i=0;i<game.getMap().getBricks().length;i++){
            for (int j=0;j<game.getMap().getBricks()[0].length;j++){
                if (game.getMap().getBricks()[i][j]!=null){//TODO connaitre le nombres de briques tt ça la je parcours toute la map c pas bon je dois m'arreter à la dernière brique
                    addBrick(game.getMap().getBricks()[i][j]);
                }
            }
        }

        // this.graphBrickSet = new BrickSet(game.getMap().getBricks());
        // this.graphBall = new BallGraphics(game.getBall());
        // rectangle rond losange triangle
        this.graphRacket = new RacketGraphics(game.getRacket(), game.getRacket().getShapeType());
        if (GameConstants.PARTICLES) {
            this.particleGroup = new ParticleGroup(root, game);
        }
        // this.root.getChildren().add(graphBrickSet);
        // this.root.getChildren().add(graphBall);
        this.root.getChildren().add(graphRacket.getShape());
        this.updateEntitiesGraphics();
        root.setPrefWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        root.getStyleClass().add("game-backgorund");
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
        if (game.getRules().isColorRestricted()){
            entities.put(brick,new BricksGraphics(brick,brick.getColor()));
        }else{
            entities.put(brick, new BricksGraphics(brick));
        }
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        entities.put(ball, new BallGraphics(ball));
        ball.setZoneWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        ball.setZoneHeight(GameConstants.DEFAULT_WINDOW_HEIGHT);
    }


    private void updateEntitiesGraphics() {
        Iterator<Entry<Entity, EntityGraphics>> iterator = entities.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Entity, EntityGraphics> entry = iterator.next();
            EntityGraphics eg = entry.getValue();
            if (eg.isWaitingAdded()) {
                if (eg instanceof BricksGraphics) {
                    this.root.getChildren().add((BricksGraphics) eg);
                } else if (eg instanceof BallGraphics) {
                    this.root.getChildren().add((BallGraphics) eg);
                }
                eg.setWaitingAdded(false);
            }
            if (eg.isWaitingRemoved()) {
                if (eg instanceof BricksGraphics) {
                    this.root.getChildren().remove((BricksGraphics) eg);
                } else if (eg instanceof BallGraphics) {
                    this.root.getChildren().remove((BallGraphics) eg);
                }
                iterator.remove(); // à vérifier fonctionnement
            }
            eg.update();
        }
    }

    public void infiniteUp(ArrayList<Brick> bricks) {
        for (Brick brick : bricks) {
            addBrick(brick);
        }
    }

    public void update(long deltaT) {
        BoostAction();
        updateEntitiesGraphics();
        if (game.getRules().isInfinite()){
            infiniteUp(game.getRules().createBrickInfinite(game.getMap().getBricks()));
        }
        // graphBall.update();
        root.getChildren().remove(graphRacket.getShape());
        graphRacket.update();
        root.getChildren().add(graphRacket.getShape()); // Ajoute la forme de la raquette mise à jour
        if (GameConstants.PARTICLES) {
            particleGroup.update();
        }
        //TODO A ENLEVER
        // if (game.isInfinite()) {
            // graphBrickSet.infiniteUpdate(game.getMap());
        // } else {
        //     // graphBrickSet.update();
        // }

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
            // game.setLost(false);
            App.gameOverS.play();
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new GameOverView(primaryStage, gameView));
            level.lostAction();
        }
        if (key.getKeysPressed().contains(KeyCode.ESCAPE)) {
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new PauseView(primaryStage, gameView.getRoot(), gameRoot.getRoot(),
                    gameView.getAnimationTimer(), level));
            game.stop();
        }
        if (level.getGame().isWin()) {
            level.resetGame();
            gameView.getRoot().getChildren().add(new WinView(primaryStage, gameView, level));
            gameView.animationStop();
        }
    }

    public void BoostUpdate() {
        Iterator<Boost> iterator = game.getBoosts().iterator();
        while (iterator.hasNext()) {
            Boost boost = iterator.next();
            if (boost.move(game.getRacket().CollisionRacket(boost.getC(), game.getRacket().getShapeType()),
                    game.getRacket(), game)) {
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
