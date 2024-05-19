package gui;

import static physics.entity.Racket.AddIntensityBall;
import static physics.entity.Racket.StopBall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import config.Game;
import config.StageLevel;
import entity.Bonus;
import entity.Boost;
import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BricksGraphics;
import gui.GraphicsFactory.EntityGraphics;
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
import physics.entity.Racket;
import utils.GameConstants;
import utils.Key;

public class GameRoot {
    private Pane root = new Pane();
    private Game game;
    private Map<Entity, EntityGraphics> entities;
    private RacketGraphics graphRacket;
    private ParticleGroup particleGroup;
    private Scene scene;
    private Stage primaryStage;
    private GameView gameView;
    private StageLevel level;
    public static Key key = new Key();

    public GameRoot(StageLevel level, GameView gameView, Scene scene, Stage primaryStage) {
        this.level = level;
        this.game = level.getGame();
        this.scene = scene;
        this.gameView = gameView;
        this.primaryStage = primaryStage;

        root.setPrefHeight(GameConstants.DEFAULT_WINDOW_HEIGHT);
        root.setPrefWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);

        entities = new HashMap<>(); // Stocke l'entité et son graphic associé

        this.graphRacket = new RacketGraphics(game.getRacket(), game.getRacket().getShapeType());
        if (GameConstants.PARTICLES) {
            this.particleGroup = new ParticleGroup(root, game);
        }
        addEntitiesGraphics(); // Affichage du début
        this.root.getChildren().add(graphRacket.getShape());
        this.updateEntitiesGraphics();
        root.setPrefWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        root.getStyleClass().add("game-backgorund");
    }

    private void addBrick(Brick brick) {
        if (game.getRules().isColorRestricted()) {
            entities.put(brick, new BricksGraphics(brick, brick.getColor()));
        } else {
            entities.put(brick, new BricksGraphics(brick));
        }
    }

    private void addBall(Ball ball) {
        entities.put(ball, new BallGraphics(ball));
        ball.setZoneWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        ball.setZoneHeight(GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    private void addEntitiesGraphics() { // Ajoute les entités non ajoutées
        for (Ball b : game.getBalls()) {
            if (!entities.containsKey(b)) {
                addBall(b);
            }
        }
        for (Brick b : game.getMap().getBricks()) {
            if (!entities.containsKey(b)) {
                addBrick(b);
            }
        }
    }

    private void updateEntitiesGraphics() { // Met à jour les entités
        Iterator<Entry<Entity, EntityGraphics>> iterator = entities.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Entity, EntityGraphics> entry = iterator.next();
            EntityGraphics eg = entry.getValue();
            if (eg.isWaitingAdded()) {
                if (eg instanceof BricksGraphics) {
                    root.getChildren().add((BricksGraphics) eg);
                } else if (eg instanceof BallGraphics) {
                    root.getChildren().add((BallGraphics) eg);
                }
                eg.setWaitingAdded(false);
            }
            if (eg.isWaitingRemoved()) {
                if (eg instanceof BricksGraphics) {
                    root.getChildren().remove((BricksGraphics) eg);
                } else if (eg instanceof BallGraphics) {
                    root.getChildren().remove((BallGraphics) eg);
                }
                iterator.remove();
            }
            eg.update();
        }
    }

    // public void infiniteUp(ArrayList<Brick> bricks) {
    //     for (Brick brick : bricks) {
    //         addBrick(brick);
    //     }
    // }

    public void update(long deltaT) {
        BoostAction();
        addEntitiesGraphics();
        updateEntitiesGraphics();
        // if (game.getRules().isInfinite()) {
        //     infiniteUp(game.getRules().createBrickInfinite(game.getMap().getBricks()));
        // }
        graphRacket.update();
        if (GameConstants.PARTICLES) {
            particleGroup.update();
        }

        BonusUpdate();
        key.handleInput(game);
        key.touchesR(scene, game);
        int life = game.getLife();
        Racket.d = key.getKeysPressed();
        game.update(deltaT);
        key.touchesM(scene, game);
        if (life != game.getLife()) {
            root.getChildren().removeIf(e -> e instanceof Bonus);
        }
        if (game.isLost()) {
            App.gameOverS.play();
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new GameOverView(primaryStage, gameView));
            level.lostAction();
        }
        if (key.getKeysPressed().contains(KeyCode.ESCAPE)) {
            gameView.animationStop();
            gameView.getRoot().getChildren().add(new PauseView(primaryStage, gameView.getRoot(), getRoot(),
                    gameView.getAnimationTimer(), level));
            game.stop();
        }
        if (level.getGame().isWin()) {
            level.resetGame();
            gameView.getRoot().getChildren().add(new WinView(primaryStage, gameView, level));
            gameView.animationStop();
        }
    }

    public void BonusUpdate() {
        Iterator<Bonus> iterator = game.getBoosts().iterator();
        while (iterator.hasNext()) {
            Bonus bonus = iterator.next();
            if (bonus instanceof Boost) {
                Boost boost = (Boost) bonus;
                if (boost.move(game.getRacket().CollisionRacket(boost.getC(), game.getRacket().getShapeType()),
                        game.getRacket(), game.getBalls())) {
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
            } else {
                if (bonus.move(game.getRacket().CollisionRacket(bonus.getC(), game.getRacket().getShapeType()),
                        game.getRacket(),game.getBalls())) {
                    root.getChildren().remove(bonus);
                    iterator.remove();
                } else {
                    if (!root.getChildren().contains(bonus)) {
                        root.getChildren().add(bonus);
                    }
                    if (bonus.getY() > GameConstants.DEFAULT_WINDOW_HEIGHT) {
                        root.getChildren().remove(bonus);
                        iterator.remove();
                    }
                }
            }
        }
    }

    // gere les modifiactaion fait par les boosts
    public void BoostAction() {
        if (StopBall) {
            for (Ball ball : game.getBalls()) {
                ball.setSpeed(0);
                ball.setDirection(new physics.geometry.Vector(0, 1));
            }
        } else if (AddIntensityBall) {
            for (Ball ball : game.getBalls()) {
                ball.setSpeed(GameConstants.DEFAULT_BALL_SPEED * GameConstants.BOOST_INTENSITY_BALL);
            }
        } else {
            for (Ball ball : game.getBalls()) {
                ball.setSpeed(GameConstants.DEFAULT_BALL_SPEED);
            }
        }
    }

    public Pane getRoot() {
        return root;
    }

    public Game getGame() {
        return game;
    }

    // public void updateGraphics() {
    //     for (BallGraphics ball : graphBall) {
    //         ball.update();
    //     }
    //     for (int i = 0; i < game.getBalls().size(); i++) {
    //         Ball ball = game.getBalls().get(i);
    //         if (!balls.contains(ball)) {
    //             BallGraphics ballg = new BallGraphics(ball);
    //             graphBall.add(ballg);
    //             balls.add(ball);
    //             root.getChildren().add(ballg);
    //         }
    //         if (ball.delete()) {
    //             root.getChildren().remove(graphBall.get(i));
    //             graphBall.remove(i);
    //             balls.remove(i);
    //         }
    //     }

    // }
}
