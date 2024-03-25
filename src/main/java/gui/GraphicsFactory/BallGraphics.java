package gui.GraphicsFactory;

import physics.entity.Ball;
import utils.ImageCache;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BallGraphics extends ImageView {

    private Ball ball;
    private Image classicBall = ImageCache.getImage("/balle/balle1.png");
    private Image hyperBall = ImageCache.getImage("/balle/balleHyper.png");
    private Image gravityBall = ImageCache.getImage("/balle/balleGravity.png");
    private Image positifBall = ImageCache.getImage("/balle/positif.png");
    private Image negatifBall = ImageCache.getImage("/balle/negatif.png");
    private Image redBall = ImageCache.getImage("/balle/balleRouge.png");
    private Image greenBall = ImageCache.getImage("/balle/balleVert.png");
    private Image blueBall = ImageCache.getImage("/balle/balleBleu.png");
    

    public BallGraphics(Ball ball) {
        this.ball = ball;
        setImageAndProperties();
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
    }

    public void update() {
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        updateImageAndProperties();
    }

    private void setImageAndProperties() {
        if (ball instanceof ClassicBall) {
            setImage(classicBall);
        } else if (ball instanceof HyperBall) {
            setImage(hyperBall);
        } else if (ball instanceof GravityBall) {
            setImage(gravityBall);
        } else if (ball instanceof MagnetBall) {
            setImage(positifBall);
        }
        setFitWidth(ball.getRadius() * 2);
        setPreserveRatio(true);
        setSmooth(true);
    }

    private void updateImageAndProperties() {
        if (ball instanceof MagnetBall) {
            if (((MagnetBall) ball).getEtat().equals("positif")) {
                setImage(positifBall);
            } else {
                setImage(negatifBall);
            }
        }
        if (ball.getColor() != null) {
            switch (ball.getColor()) {
                case RED:
                    setImage(redBall);
                    break;
                case GREEN:
                    setImage(greenBall);
                    break;
                case BLUE:
                    setImage(blueBall);
                    break;
            }
        }
    }
}