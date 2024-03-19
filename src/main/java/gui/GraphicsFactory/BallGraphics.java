package gui.GraphicsFactory;

import physics.entity.Ball;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BallGraphics extends ImageView {

    private Ball ball;
    private Image classicBall = new Image("/balle/balleGravity.png");
    private Image hyperBall = new Image("/balle/balleHyper.png");
    private Image gravityBall = new Image("/balle/balleGravity.png");
    private Image positifBall = new Image("/balle/positif.png");
    private Image negatifBall = new Image("/balle/negatif.png");
    private Image redBall = new Image("/balle/balleRouge.png");
    private Image greenBall = new Image("/balle/balleVert.png");
    private Image blueBall = new Image("/balle/balleBleu.png");

    public BallGraphics(Ball ball) {
        this.ball = ball;
        updateImageAndProperties();
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
    }

    public void update() {
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        updateImageAndProperties();
    }

    private void updateImageAndProperties() {
        if (ball instanceof ClassicBall) {
            setImage(classicBall);
            setFitWidth(ball.getRadius() * 3);
            setFitHeight(ball.getRadius() * 3);
            ball.setRadius(ball.getRadius() * 3);
        } else if (ball instanceof HyperBall) {
            setImage(hyperBall);
            setFitWidth(ball.getRadius() * 3);
            setFitHeight(ball.getRadius() * 3);
            ball.setRadius(ball.getRadius() * 3);
        } else if (ball instanceof GravityBall) {
            setImage(gravityBall);
            setFitWidth(ball.getRadius() * 3);
            setFitHeight(ball.getRadius() * 3);
            ball.setRadius(ball.getRadius() * 3);
        } else if (ball instanceof MagnetBall) {
            if (((MagnetBall) ball).getEtat().equals("positif")) {
                setImage(positifBall);
                setFitWidth(ball.getRadius() * 3);
                setFitHeight(ball.getRadius() * 3);
                ball.setRadius(ball.getRadius() * 3);
            } else {
                setImage(negatifBall);
                setFitWidth(ball.getRadius() * 3);
                setFitHeight(ball.getRadius() * 3);
                ball.setRadius(ball.getRadius() * 3);
            }
        }
        if (ball.getColor() != null) {
            switch (ball.getColor()) {
                case RED:
                    setImage(redBall);
                    setFitWidth(ball.getRadius() * 4);
                    setFitHeight(ball.getRadius() * 4 );
                    ball.setRadius(ball.getRadius() * 4);
                    break;
                case GREEN:
                    setImage(greenBall);
                    setFitWidth(ball.getRadius() * 4);
                    setFitHeight(ball.getRadius() * 4);
                    ball.setRadius(ball.getRadius() * 4);
                    break;
                case BLUE:
                    setImage(blueBall);
                    setFitWidth(ball.getRadius() * 4);
                    setFitHeight(ball.getRadius() * 4);
                    //ball.setRadius(ball.getRadius() * 4);
                    break;
            }
        }
        // setFitWidth(ball.getRadius() * 3);
        // setFitHeight(ball.getRadius() * 3);
        setPreserveRatio(true);
        setSmooth(true);
        // setCache(true);
    }
}