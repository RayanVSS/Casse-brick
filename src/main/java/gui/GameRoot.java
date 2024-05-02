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
import gui.Menu.MenuViews.WinView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.GameConstants;
import utils.Key;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import physics.entity.Ball;
import entity.Bonus;

//pour les boosts
import static physics.entity.Racket.StopBall;
import static physics.entity.Racket.AddIntensityBall;;

public class GameRoot {
    private Pane root = new Pane();
    private Game game;
    private BrickSet graphBrickSet;
    private List<BallGraphics> graphBall;
    private List<Ball> balls;
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
        balls = new ArrayList<>();
        this.graphBall = init_GraphicsBall(game.getBalls(),balls);
        // rectangle rond losange triangle
        this.graphRacket = new RacketGraphics(game.getRacket(), game.getRacket().getShapeType());
        if (GameConstants.PARTICLES) {
            this.particleGroup = new ParticleGroup(root, game);
        }
        this.root.getChildren().add(graphBrickSet);
        for (BallGraphics ball : graphBall) {
            this.root.getChildren().add(ball);
        }
        this.root.getChildren().add(graphRacket.getShape());
        root.setPrefWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        root.getStyleClass().add("game-backgorund");
    }

    public void update(long deltaT) {
        BoostAction();
        updateGraphics();
        root.getChildren().remove(graphRacket.getShape());
        graphRacket.update();
        root.getChildren().add(graphRacket.getShape()); // Ajoute la forme de la raquette mise à jour
        if (GameConstants.PARTICLES) {
            particleGroup.update();
        }
        graphBrickSet.update();
        BonusUpdate();
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

    public void BonusUpdate() {
        Iterator<Bonus> iterator = game.getBoosts().iterator();
        while (iterator.hasNext()) {
            Bonus bonus = iterator.next();
            if(bonus instanceof Boost){
                Boost boost = (Boost) bonus;
                if (boost.move(game.getRacket().CollisionRacket(boost.getC(),game.getRacket().getShapeType()), game.getRacket())) {
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
            else{
                if (bonus.move(game.getRacket().CollisionRacket(bonus.getC(),game.getRacket().getShapeType()), game.getRacket())){
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

    public static List<BallGraphics> init_GraphicsBall(List<Ball> ballList,List<Ball> balls) {
        List<BallGraphics> graphBalls = new ArrayList<>();
        for (Ball ball : ballList) {
            graphBalls.add(new BallGraphics(ball));
            balls.add(ball);
        }
        return graphBalls;
    }

    public void updateGraphics() {
        for (BallGraphics ball : graphBall) {
            ball.update();
        }
        for (int i=0;i<game.getBalls().size();i++){
            Ball ball = game.getBalls().get(i);
            if (!balls.contains(ball)) { 
                BallGraphics ballg = new BallGraphics(ball);
                graphBall.add(ballg);
                balls.add(ball);
                root.getChildren().add(ballg);
            }
            if(ball.delete()){
                root.getChildren().remove(graphBall.get(i));
                graphBall.remove(i);
                balls.remove(i);
            }
        }

        
    }
}
