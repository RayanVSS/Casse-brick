package gui.GraphicsFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import physics.entity.Ball;
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
            getStyleClass().add("ball");
        else if (ball instanceof HyperBall)
            getStyleClass().add("hyperball");
        else if (ball instanceof GravityBall)
            getStyleClass().add("gravityball");
        else if (ball instanceof MagnetBall)
            getStyleClass().add("magnetball");
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
        if (ball.getColor() != null) {
            switch (ball.getColor()) {
                case RED:
                    setFill(Color.RED);
                    break;
                case GREEN:
                    setFill(Color.GREEN);
                    break;
                case BLUE:
                    setFill(Color.BLUE);
                    break;
                default:
                    setFill(Color.GRAY);
                    break;
            }
        }

    }

}
