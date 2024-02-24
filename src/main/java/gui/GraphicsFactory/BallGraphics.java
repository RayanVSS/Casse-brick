package gui.GraphicsFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;


public class BallGraphics extends Circle {

    private Ball ball;

    public BallGraphics(Ball ball) {
        this.ball = ball;
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
        setRadius(ball.getRadius());
        if (ball instanceof ClassicBall)
            setFill(Color.RED);
        else if (ball instanceof HyperBall)
            setFill(Color.BLUE);
        else if (ball instanceof GravityBall)
            setFill(Color.GRAY);
        else if (ball instanceof MagnetBall)
            setFill(Color.GREEN);
        else
            setFill(Color.BLACK);
    }

    public void update() {
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
        if (ball instanceof MagnetBall) {
            if (((MagnetBall) ball).getEtat().equals("positif"))
                setFill(Color.GREEN);
            else
                setFill(Color.YELLOW);
        }
    }

    
}
