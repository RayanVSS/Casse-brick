package gui;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import config.*;
import entity.ball.*;
import entity.racket.*;
import gui.GraphicsFactory.*;
import utils.*;


public class GameView {
    private Stage primaryStage;
    private BorderPane root;
    private SLFPSGraphics SLFPS;
    private Scene scene;
    private GameRoot gameRoot;
    private Game game = new Game(new ClassicBall(), new ClassicRacket(), BricksArrangement.DEFAULT);
    // lire les touches
    Key key = new Key();
    // fps & lifeScore
    private FPSGraphics fpsGraphics=new FPSGraphics();
    private ScoreLifeGraphics scoreLifeView = new ScoreLifeGraphics(game);
    // animation
    private AnimationTimer animationTimer;

    public GameView(Stage p, int level) {
        this.primaryStage = p;
        this.root = new BorderPane();
        this.SLFPS=new SLFPSGraphics(scoreLifeView, fpsGraphics);
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        this.gameRoot = new GameRoot(game, this, scene, primaryStage);
        this.scoreLifeView = new ScoreLifeGraphics(game);
        gameRoot.getRoot().setPrefHeight(GameConstants.DEFAULT_WINDOW_HEIGHT - this.scoreLifeView.getPrefHeight());
        gameRoot.getRoot().setPrefWidth(GameConstants.DEFAULT_WINDOW_WIDTH - this.scoreLifeView.getPrefWidth());
        this.root.setCenter(gameRoot.getRoot());
        this.root.setLeft(scoreLifeView);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        scene.getStylesheets().add(GameConstants.CSS);
        animation();
    }

    public void animation() {
        animationTimer = new AnimationTimer() {
            long last = 0;
            double delay = 0.0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                var deltaT = now - last;
                // laisser un delai de 2 seconde avant de deplacer la balle
                if (delay < 2.0) {
                    delay += deltaT / 1000000000.0;
                } else if (now - last > 1000000000 / 120) {
                    gameRoot.animation(deltaT);
                    SLFPS.update();
                }
                last = now;
            }
        };
        animationTimer.start();
    }

    public void animationStop() {
        animationTimer.stop();
    }

    public Pane getRoot() {
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }
}
