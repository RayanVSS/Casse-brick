package physics;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import entity.ball.Ball;
import geometry.Coordinates;
import geometry.Vector;
import javafx.animation.AnimationTimer;
import utils.GameConstants;
import javafx.scene.paint.Color;


public class Simulation {

    public static boolean PATH=false; 
    
    Ball ball;
    Circle graphBall;
    Stage primaryStage;
    Outline outline;
    Pane root;

    public Simulation(Outline outline , Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new Pane();
        primaryStage.setScene(new javafx.scene.Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT));
        this.outline = outline;
        ball = new Ball(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
        outline.getDirection(),1, outline.getDiametre()){
            @Override
            public boolean movement() {
                double h = GameConstants.DEFAULT_WINDOW_WIDTH;
                double w = GameConstants.DEFAULT_WINDOW_HEIGHT;
                double newX = this.getC().getX() + this.getDirection().getX()* outline.getVitesse().getX() + outline.getVent().getX() ;
                double newY = this.getC().getY() + this.getDirection().getY()* outline.getVitesse().getY() + outline.getGravite()*outline.getMass() + outline.getVent().getY() ;

                if (newX < 0 || newX > h - this.getRadius()) {
                    this.getDirection().setX(-this.getDirection().getX());
                    newX = this.getC().getX() + this.getDirection().getX();
                }
                if (newY < 0 || newY > w - this.getRadius()) {
                    this.getDirection().setY(-this.getDirection().getY());
                    newY = this.getC().getY() + this.getDirection().getY();
                }
                this.setC(new Coordinates(newX, newY));
                this.getDirection().setY(getDirection().getY()+outline.getGravite()*outline.getMass());;

                return true;
            }
        };


        graphBall = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getRadius() * 2);

        root.getChildren().add(graphBall);
        primaryStage.show();

        if(PATH){
            for(int i=0;i<=50;i++){
                Coordinates c = ball.trajectory(outline);
                if(i%5==0){
                    Circle c1 = new Circle(c.getX(), c.getY(), 2);
                    c1.setFill(Color.BLUE);
                    root.getChildren().add(c1);
                    ball.circles.add(c1);
                }
            }
        }

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
        if(PATH){
            Coordinates c = ball.trajectory(outline);
            if(ball.trajectory>=5){
                ball.trajectory=0;
                Circle c1 = new Circle(c.getX(), c.getY(), 2);
                c1.setFill(Color.BLUE);
                root.getChildren().add(c1);
                ball.circles.add(c1);
                root.getChildren().remove(ball.circles.get(0));
                ball.circles.remove(0);
            }
        }

        graphBall.setCenterX(ball.getC().getX());
        graphBall.setCenterY(ball.getC().getY());
    }

}