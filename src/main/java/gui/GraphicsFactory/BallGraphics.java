package gui.GraphicsFactory;

import physics.entity.Ball;
import physics.entity.Entity;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BallGraphics extends ImageView implements EntityGraphics {
    private static final Image CLASSIC_BALL = ImageLoader.loadImage("src/main/ressources/balle/balle1.png");
    private static final Image HYPER_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleHyper.png");
    private static final Image GRAVITY_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleGravity.png");
    private static final Image POSITIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/positif.png");
    private static final Image NEGATIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/negatif.png");
    private static final Image RED_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleRouge.png");
    private static final Image GREEN_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleVert.png");
    private static final Image BLUE_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleBleu.png");

    private Ball ball;
    private Image currentImage;
    private boolean waitingAdded, waitingRemoved;

    public BallGraphics(Ball ball) {
        this.ball = ball;
        setImageAndProperties();
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        waitingAdded = true;
    }

    public void update() {
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        updateImageAndProperties();
    }

    private void setImageAndProperties() {
        if (ball instanceof ClassicBall) {
            currentImage = CLASSIC_BALL;
        } else if (ball instanceof HyperBall) {
            currentImage = HYPER_BALL;
        } else if (ball instanceof GravityBall) {
            currentImage = GRAVITY_BALL;
        } else if (ball instanceof MagnetBall) {
            currentImage = POSITIF_BALL;
        }
        setImage(currentImage);
        setFitWidth(ball.getRadius() * 2);
        setPreserveRatio(true);
        setSmooth(true);
    }

    private void updateImageAndProperties() {
        if (ball instanceof MagnetBall) {
            Image newImage = ((MagnetBall) ball).getEtat().equals("positif") ? POSITIF_BALL : NEGATIF_BALL;
            if (newImage != currentImage) {
                currentImage = newImage;
                setImage(currentImage);
            }
        }
        if (ball.getColor() != null) {
            Image newImage;
            switch (ball.getColor()) {
                case RED:
                    newImage = RED_BALL;
                    break;
                case GREEN:
                    newImage = GREEN_BALL;
                    break;
                case BLUE:
                    newImage = BLUE_BALL;
                    break;
                default:
                    newImage = currentImage;
            }
            if (newImage != currentImage) {
                currentImage = newImage;
                setImage(currentImage);
            }
        }
    }

    public Entity getEntity() {
        return ball;
    }

    public boolean isWaitingAdded() {
        return waitingAdded;
    }

    public void setWaitingAdded(boolean waitingAdded) {
        this.waitingAdded = waitingAdded;
    }

    public boolean isWaitingRemoved() {
        return waitingRemoved;
    }

    public void setWaitingRemoved(boolean waitingRemoved) {
        this.waitingRemoved = waitingRemoved;
    }

}
