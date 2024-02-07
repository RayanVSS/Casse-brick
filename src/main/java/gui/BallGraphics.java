package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import entity.ball.Ball;
public class BallGraphics extends Circle{
    
        private Ball ball;
    
        public BallGraphics(Ball ball) {
            this.ball = ball;
            setCenterX(ball.getC().getX());
            setCenterY(ball.getC().getY());
            setRadius(ball.getRadius());
            setFill(Color.RED);
        }
    
        public void update() {
            setCenterX(ball.getC().getX());
            setCenterY(ball.getC().getY());
        }
}
