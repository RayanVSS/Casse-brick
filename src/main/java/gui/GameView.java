package gui;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import config.*;
import entity.Particle;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class GameView extends App {

    private Stage primaryStage;
    private Pane root = new Pane();
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private Game game;
    
    // Balle
    private Circle graphBall;

    // Raquette
    private Rectangle graphRacket;
    private final Set<KeyCode> keysPressed = new HashSet<>();

    // Particules de traînée
    private List<List<Particle>> particleTrails = new ArrayList<>();
    private int trailLength = GameConstants.DEFAULT_trailLength;

    //lire les touches
    Key key = new Key();


    public GameView(Stage p, int level) {

        this.primaryStage = p;

        /* differentes balles *

        Ball ball = new GravityBall(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
                randomDirection(), GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_DIAMETER);
                
        Ball ball = new HyperBall(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
                randomDirection(), GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_DIAMETER);
        */

        Ball ball = new ClassicBall(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
                randomDirection(), GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_DIAMETER);


        // Création des particules
        for (int i = 0; i < trailLength; i++) {
            List<Particle> trail = new ArrayList<>();
            for (int j = 0; j < GameConstants.DEFAULT_PARTICLE; j++) { // nombre de particules
                Particle particle = new Particle(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2);
                trail.add(particle);
                root.getChildren().add(particle);
                           }
            particleTrails.add(trail);
        }

        game = new Game(ball, new Racket(1), BricksArrangement.DEFAULT);

        // Création des éléments graphiques
        this.graphBall = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getDiametre() * 2);

        this.graphRacket = new Rectangle(game.getRacket().getC().getX(), game.getRacket().getC().getY(),
                game.getRacket().getLongueur(), game.getRacket().getLargeur());

        // Ajout des éléments graphiques à la fenêtre
        root.getChildren().add(this.graphBall);
        root.getChildren().add(this.graphRacket);

        // Affichage de la fenêtre
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;
            double delay = 0.0;
            @Override
            public void handle(long now) {
                key.touches(scene, game);
                key.handleInput(game);
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                var deltaT = now - last;

                // System.out.println("deltaT = " + (now - last) / 1000000000.0 + "s");

                // laisser un delai de 2 seconde avant de deplacer la balle
                if (delay < 2.0) {
                    delay += deltaT / 1000000000.0;
                }

                if (now - last > 1000000000 / 120) {
                    game.update(deltaT);
                    update();
                }
                last = now;
            }
        };
        animationTimer.start();
    }



    public void update() {
        // Mise à jour de la position de la balle
        this.graphBall.setCenterX(game.getBall().getC().getX());
        this.graphBall.setCenterY(game.getBall().getC().getY());

        // Mise à jour de la position de la raquette
        this.graphRacket.setX(game.getRacket().getC().getX());
        this.graphRacket.setY(game.getRacket().getC().getY());


        // Mise à jour des particules de traînée
        for (int i = 0; i < trailLength; i++) {
            List<Particle> trail = particleTrails.get(i);
    
            for (int j = trail.size() - 1; j > 0; j--) {
                Particle currentParticle = trail.get(j);
                Particle previousParticle = trail.get(j - 1);
    
                currentParticle.setCenterX(previousParticle.getCenterX());
                currentParticle.setCenterY(previousParticle.getCenterY());
                currentParticle.applyRandomFluctuation(); //fluctuation des particules
            }
    
            Particle firstParticle = trail.get(0);
            firstParticle.setCenterX(game.getBall().getC().getX());
            firstParticle.setCenterY(game.getBall().getC().getY());
            firstParticle.applyRandomFluctuation(); // Appliquer la fluctuation
        }
    }

    // Génère une direction aléatoire pour la balle
    public Vector randomDirection() {
        Random random = new Random();
        double i = -1 + (1 - (-1)) * random.nextDouble();
        double j = -1 + (1 - (-1)) * random.nextDouble();
        return new Vector(new Coordinates(i, j));
    }

}
