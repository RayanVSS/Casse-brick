package gui;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import config.*;
import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;
import utils.*;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class GameView extends App {

    private Stage primaryStage;
    private Pane root = new Pane();
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private Game game;

    // Balle
    private BallGraphics graphBall;

    // Raquette
    private Rectangle graphRacket;
    public static boolean BougePColision;
    public static Set<KeyCode> direction = new HashSet<>();

    // lire les touches
    Key key = new Key();

    // fps
    private FPS fpsCalculator = new FPS();
    private Text fpsText = new Text();
    private Text maxfpsText = new Text();

    // animation
    AnimationTimer animationTimer;

    public GameView(Stage p, int level) {

        this.primaryStage = p;

        /* differentes balles */

        Ball ball = new GravityBall(GameConstants.DEFAULT_BALL_START_COORDINATES,
                GameConstants.DEFAULT_BALL_START_DIRECTION, GameConstants.DEFAULT_BALL_SPEED,
                GameConstants.DEFAULT_BALL_RADIUS);
        Ball Cball = new ClassicBall(GameConstants.DEFAULT_BALL_START_COORDINATES,
                GameConstants.DEFAULT_BALL_START_DIRECTION, GameConstants.DEFAULT_BALL_SPEED,
                GameConstants.DEFAULT_BALL_RADIUS);
        game = new Game(Cball, new Racket(2), BricksArrangement.DEFAULT);

        // Création des éléments graphiques
        this.graphBall = new BallGraphics(game.getBall());

        this.graphRacket = new Rectangle(game.getRacket().getC().getX(), game.getRacket().getC().getY(),
                game.getRacket().getHeight(), game.getRacket().getWidth());

        // Ajout des éléments graphiques à la fenêtre
        root.getChildren().add(this.graphBall);
        root.getChildren().add(this.graphRacket);

        // Ajout du texte des FPS
        fpsText.setX(10);
        fpsText.setY(20);
        root.getChildren().add(fpsText);

        // Ajout du texte des FPS max
        maxfpsText.setX(10);
        maxfpsText.setY(40);
        root.getChildren().add(maxfpsText);

        // Affichage de la fenêtre
        primaryStage.setScene(scene);
        primaryStage.show();

        animation();
    }

    public void update() {
        // Mise à jour de la position de la balle
        this.graphBall.update();

        // Mise à jour de la position de la raquette
        this.graphRacket.setX(game.getRacket().getC().getX());
        this.graphRacket.setY(game.getRacket().getC().getY());

        // Calcul des FPS
        double fps = fpsCalculator.calculateFPS();
        double maxfps = fpsCalculator.getMaxFps();
        // Mise à jour du texte FPS
        fpsText.setText("FPS: " + String.format("%.2f", fps));
        maxfpsText.setText("Max FPS: " + String.format("%.2f", maxfps));

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
                    direction = key.getKeysPressed();
                    game.update(deltaT);
                    update();
                    key.touchesM(scene, game);
                }
                last = now;
            }
        };
        animationTimer.start();
    }

    public void animationStop() {
        animationTimer.stop();
    }

}
