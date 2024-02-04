package gui;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import config.*;
import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;

import java.util.Random;

public class GameView extends App {

    private Stage primaryStage;
    private Scene scene;
    private Pane root;
    private Game game;
    private Circle graphBall;

    public GameView(Stage p, int level) {
        // TODO implement here
        this.primaryStage = p;
        this.root = new Pane();
        Random random = new Random();

        // randomiser la direction de la balle
        double i = -1 + (1 - (-1)) * random.nextDouble();
        double j = -1 + (1 - (-1)) * random.nextDouble();

        Vector v = new Vector(new Coordinates(i, j));
        Ball ball = new ClassicBall(new Coordinates(primaryStage.getWidth()/3, primaryStage.getHeight()/3),v,5,5);

        game = new Game(ball,new Racket(1));

        this.graphBall = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getDiametre() * 2);

        root.getChildren().add(this.graphBall);
        this.scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;
            double delay=0.0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }

                var deltaT = now - last;
                
                System.out.println("deltaT = " + (now - last) / 1000000000.0 + "s");
                
                // laisser un delai de 5 seconde avant de deplacer la balle
                if (delay < 4.0) {
                    delay+=deltaT/1000000000.0;
                }else{
                    game.update(deltaT, root.getWidth(), root.getHeight());
                    update();
                }
                last = now;
            }
        };
        animationTimer.start();
    }

    public void update() {
        // TODO implement here
        Ball ball = game.getBall();
        this.graphBall.setCenterX(ball.getC().getX());
        this.graphBall.setCenterY(ball.getC().getY());
    }

}
