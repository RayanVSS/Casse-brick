package physics;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import entity.ball.Ball;
import geometry.Coordinates;
import javafx.animation.AnimationTimer;
import utils.GameConstants;


public class Simulation {
    Ball ball;
    Circle graphBall;
    Stage primaryStage;

    public Simulation(Outline outline , Stage primaryStage) {
        this.primaryStage = primaryStage;
        Pane root = new Pane();
        primaryStage.setScene(new javafx.scene.Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT));

        ball = new Ball(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
        outline.getDirection(),outline.getVitesse(), outline.getDiametre()){
            @Override
            public boolean movement() {
                double h = GameConstants.DEFAULT_WINDOW_WIDTH;
                double w = GameConstants.DEFAULT_WINDOW_HEIGHT;
                double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed() + outline.getVent().getX();
                double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed() + outline.getVent().getY();

                if (newX < 0 || newX > h - this.getRadius()) {
                    this.getDirection().setX(-this.getDirection().getX());
                    newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                }
                if (newY < 0 || newY > w - this.getRadius()) {
                    this.getDirection().setY(-this.getDirection().getY());
                    newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                }
                this.setC(new Coordinates(newX, newY));
                this.getDirection().add(outline.getGravite());

                return true;
            }
        };


        graphBall = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getRadius() * 2);

        root.getChildren().add(graphBall);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;
            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                var deltaT = now - last;

                // System.out.println("deltaT = " + (now - last) / 1000000000.0 + "s");
                if (deltaT > 1000000000 / 120) {
                   update();
                   
                }
                last = now;
            }
        };
        animationTimer.start();

    }

    public void update() {
        // Mise à jour de la position de la balle
        ball.movement();
        graphBall.setCenterX(ball.getC().getX());
        graphBall.setCenterY(ball.getC().getY());
    }

}