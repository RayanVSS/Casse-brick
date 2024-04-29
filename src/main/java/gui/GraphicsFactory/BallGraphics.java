package gui.GraphicsFactory;

import utils.GameConstants;
import entity.ball.*;
import gui.ImageLoader;
import gui.Menu.Menu.Theme;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import physics.entity.Ball;
import physics.entity.Entity;

public class BallGraphics extends Circle implements EntityGraphics {
    private static final String IMAGE_PATH = "src/main/ressources/balle/";
    private static final String POSITIF_BALL = "positif.png";
    private static final String NEGATIF_BALL = "negatif.png";
    private static final String RED_BALL = IMAGE_PATH + "red.png";
    private static final String GREEN_BALL = IMAGE_PATH + "green.png";
    private static final String BLUE_BALL = IMAGE_PATH + "blue.png";

    private Ball ball;
    private Image currentImage;
    private boolean waitingAdded, waitingRemoved;

    public BallGraphics(Ball ball) {
        super(ball.getRadius());
        this.ball = ball;
        setImageAndProperties();
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
        waitingAdded = true;
    }

    public void update() {
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
        updateImageAndProperties();
    }

    private void setImageAndProperties() {
        setImageBasedOnCSS(GameConstants.CSS);
    }

    private void setImageBasedOnCSS(Theme css) {
        String imagePath = IMAGE_PATH + css.getName().toLowerCase() + "/";
        if(css.equals(Theme.ACHROMATOPSIE)||css.equals(Theme.DEUTERANOPIE)||css.equals(Theme.PROTANOPIE)||css.equals(Theme.TRITANOPIE)){
            imagePath = IMAGE_PATH + "light/";
        }
        if (ball instanceof ClassicBall) {
            GameConstants.BALL_PATH = imagePath + "classic.png";
            setImage(GameConstants.BALL_PATH);
        } else if (ball instanceof HyperBall) {
            GameConstants.BALL_PATH = imagePath + "hyper.png";
            setImage(GameConstants.BALL_PATH);
        } else if (ball instanceof GravityBall) {
            GameConstants.BALL_PATH = imagePath + "gravity.png";
            setImage(GameConstants.BALL_PATH);
        } else if (ball instanceof MagnetBall) {
            setImage(imagePath + ((MagnetBall) ball).getEtat().equals("positif") != null ? POSITIF_BALL : NEGATIF_BALL);
        }
    }

    private void updateImageAndProperties() {
        if (ball instanceof MagnetBall) {
            String newImagePath = ((MagnetBall) ball).getEtat().equals("positif") ? POSITIF_BALL : NEGATIF_BALL;
            setImage(newImagePath);
        }
        if (ball.getColor() != null) {
            switch (ball.getColor()) {
                case RED:
                    setImage(RED_BALL);
                    break;
                case GREEN:
                    setImage(GREEN_BALL);
                    break;
                case BLUE:
                    setImage(BLUE_BALL);
                    break;
                default:
                    break;
            }
        }
    }

    private void setImage(String imagePath) {
        Image newImage = ImageLoader.loadImage(imagePath);
        if (newImage != currentImage) {
            currentImage = newImage;
            ImagePattern imagePattern = new ImagePattern(currentImage);
            this.setFill(imagePattern);
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