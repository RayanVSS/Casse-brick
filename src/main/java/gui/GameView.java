package gui;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import config.*;
import entity.Particle;
import entity.ball.*;
import entity.racket.*;
import geometry.Vector;
import gui.GraphicsFactory.*;
import gui.Menu.MenuViews.GameOverView;
import gui.Menu.MenuViews.ScoreLifeView;
import utils.*;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import geometry.Coordinates;
import gui.Menu.MenuViews.PauseView;

public class GameView extends App {

    private Stage primaryStage;
    private Pane root = new Pane();
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private Game game;
    private GameView gameView;

    private BrickSet brickSet;

    // Balle
    private BallGraphics graphBall;

    // Raquette
    private RacketGraphics graphRacket;
    public static boolean BougePColision;
    public static Set<KeyCode> direction = new HashSet<>();

    // Particules de traînée
    private List<List<Particle>> particleTrails = new ArrayList<>();
    private int trailLength = GameConstants.DEFAULT_trailLength;

    // lire les touches
    Key key = new Key();

    // fps
    private FPSGraphics fpsGraphics;

    // animation
    private AnimationTimer animationTimer;
    // life & score
    private ScoreLifeView scoreLifeView;

    public GameView(Stage p, int level) {
        this.primaryStage = p;
        this.gameView = this;

        /* differentes balles */
        game = new Game(new ClassicBall(), new ClassicRacket(), BricksArrangement.DEFAULT);
        // game = new Game(new MagnetBall(), new MagnetRacket(),
        // BricksArrangement.DEFAULT);

        brickSet = new BrickSet(game.getMap().getBricks());

        // Création des particules
        if (GameConstants.PARTICLES) {
            for (int i = 0; i < trailLength; i++) {
                List<Particle> trail = new ArrayList<>();
                for (int j = 0; j < GameConstants.DEFAULT_PARTICLE; j++) { // nombre de particules
                    Particle particle = new Particle(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2);
                    trail.add(particle);
                    root.getChildren().add(particle);
                }
                particleTrails.add(trail);
            }
        }

        // Création des éléments graphiques
        this.graphBall = new BallGraphics(game.getBall());
        this.graphRacket = new RacketGraphics(game.getRacket());
        this.scoreLifeView = new ScoreLifeView(game);

        // Ajout des éléments graphiques à la fenêtre
        root.getChildren().add(this.graphBall);
        root.getChildren().add(this.graphRacket);
        root.getChildren().add(this.brickSet);
        root.getChildren().add(this.scoreLifeView);
        if (GameConstants.FPS) {
            fpsGraphics = new FPSGraphics();
            root.getChildren().add(this.fpsGraphics);
        }

        root.getStyleClass().add("game-backgorund");
        scene.getStylesheets().add(GameConstants.CSS);
        this.animation();
        // Affichage de la fenêtre
        primaryStage.setScene(scene);
    }

    public void update() {
        
        // Mise à jour de la position de la balle
        this.graphBall.update();

        // Mise à jour de la position de la raquette
        this.graphRacket.update();

        // Mise à jour de l'affich
        brickSet.update();

        // Calcul des FPS
        if (GameConstants.FPS) {
            this.fpsGraphics.update();
        }

        // Mise à jour du score et de la vie
        this.scoreLifeView.update();

        if (GameConstants.PARTICLES) {
            for (int i = 0; i < trailLength; i++) {
                List<Particle> trail = particleTrails.get(i);

                for (int j = trail.size() - 1; j > 0; j--) {
                    Particle currentParticle = trail.get(j);
                    Particle previousParticle = trail.get(j - 1);

                    currentParticle.setCenterX(previousParticle.getCenterX());
                    currentParticle.setCenterY(previousParticle.getCenterY());
                    currentParticle.applyRandomFluctuation(); // fluctuation des particules
                }
                Particle firstParticle = trail.get(0);
                firstParticle.setCenterX(game.getBall().getC().getX());
                firstParticle.setCenterY(game.getBall().getC().getY());
                firstParticle.applyRandomFluctuation(); // Appliquer la fluctuation
            }
        }
    }

    // Génère une direction aléatoire pour la balle
    public Vector randomDirection() {
        Random random = new Random();
        double i = -1 + (1 - (-1)) * random.nextDouble();
        double j = -1 + (1 - (-1)) * random.nextDouble();
        return new Vector(new Coordinates(i, j));
    }

    public void animation() {
        animationTimer = new AnimationTimer() {
            long last = 0;
            double delay = 0.0;
            // PauseView pauseView = new PauseView(primaryStage, root, this);

            @Override
            public void handle(long now) {
                BougePColision = key.isEmpty();
                key.handleInput(game);
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                var deltaT = now - last;
                // laisser un delai de 2 seconde avant de deplacer la balle
                if (delay < 2.0) {
                    delay += deltaT / 1000000000.0;
                } else if (now - last > 1000000000 / 120) {
                    key.touchesR(scene, game);
                    // prend les informations de la racquette pour la ball
                    BougePColision = key.isEmpty();
                    direction = key.getKeysPressed();
                    game.update(deltaT);
                    update();
                    key.touchesM(scene, game);
                    if (game.isLost()) {
                        game.setLost(false);
                        animationStop();
                        root.getChildren().add(new GameOverView(primaryStage, gameView).getRoot());
                    }
                    if (key.getKeysPressed().contains(KeyCode.ESCAPE)) {
                        animationStop();
                        root.getChildren().add(new PauseView(primaryStage, gameView.getRoot(), animationTimer));
                    }
                }
                last = now;
            }
        };
        animationStart();
    }

    public void animationStart() {
        System.out.println("GameView.animationStart()");
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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public Scene getScene() {
        return scene;
    }
}
