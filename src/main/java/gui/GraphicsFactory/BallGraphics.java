package gui.GraphicsFactory;

import physics.entity.Ball;
import utils.GameConstants;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BallGraphics extends ImageView {
    private static Image CLASSIC_BALL;
    private static Image HYPER_BALL;
    private static Image GRAVITY_BALL;
    private static Image POSITIF_BALL;
    private static Image NEGATIF_BALL;
    private static Image RED_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleRouge.png");
    private static Image GREEN_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleVert.png");
    private static Image BLUE_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleBleu.png");

    private Ball ball;
    private Image currentImage;

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
        switch (GameConstants.CSS) {
            case PINK:
                setPinkImage();
                break;
            case CLASSIC:
                setClassicImage();
                break;
            case LIGHT:
                setLightImage();
                break;
            case BLACK:
                setBlackImage();
                break;
            case ACHROMATOPSIE:
            case DEUTERANOPIE:
            case PROTANOPIE:
                setLightImage();
                break;
            default:
                setClassicImage();  
                break;
        }
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

    private void setPinkImage() {
        if (ball instanceof ClassicBall) {
            CLASSIC_BALL = ImageLoader.loadImage("src/main/ressources/balle/pinkClassic.png");
            currentImage = CLASSIC_BALL;
        } else if (ball instanceof HyperBall) {
            HYPER_BALL = ImageLoader.loadImage("src/main/ressources/balle/pinkHyper.png");
            currentImage = HYPER_BALL;
        } else if (ball instanceof GravityBall) {
            GRAVITY_BALL = ImageLoader.loadImage("src/main/ressources/balle/pinkGravity.png");
            currentImage = GRAVITY_BALL;
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/pinkMagnetPos.png");
            NEGATIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/pinkMagnetNeg.png");
            currentImage = POSITIF_BALL;
        }
        setImage(currentImage);
        setFitWidth(ball.getRadius() * 2);
        setPreserveRatio(true);
        setSmooth(true);
    }

    private void setClassicImage() {
        if (ball instanceof ClassicBall) {
            CLASSIC_BALL = ImageLoader.loadImage("src/main/ressources/balle/classic.png");
            currentImage = CLASSIC_BALL;
        } else if (ball instanceof HyperBall) {
            HYPER_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleHyper.png");
            currentImage = HYPER_BALL;
        } else if (ball instanceof GravityBall) {
            GRAVITY_BALL = ImageLoader.loadImage("src/main/ressources/balle/balleGravity.png");
            currentImage = GRAVITY_BALL;
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/positif.png");
            NEGATIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/negatif.png");
            currentImage = POSITIF_BALL;
        }
        setImage(currentImage);
        setFitWidth(ball.getRadius() * 2);
        setPreserveRatio(true);
        setSmooth(true);
    }

    private void setLightImage() {
        if (ball instanceof ClassicBall) {
            CLASSIC_BALL = ImageLoader.loadImage("src/main/ressources/balle/lightClassic.png");
            currentImage = CLASSIC_BALL;
        } else if (ball instanceof HyperBall) {
            HYPER_BALL = ImageLoader.loadImage("src/main/ressources/balle/lightHyper.png");
            currentImage = HYPER_BALL;
        } else if (ball instanceof GravityBall) {
            GRAVITY_BALL = ImageLoader.loadImage("src/main/ressources/balle/lightGravity.png");
            currentImage = GRAVITY_BALL;
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/lightPos.png");
            NEGATIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/lightNeg.png");
            currentImage = POSITIF_BALL;
        }
        setImage(currentImage);
        setFitWidth(ball.getRadius() * 2);
        setPreserveRatio(true);
        setSmooth(true);
    }

    private void setBlackImage() {
        if (ball instanceof ClassicBall) {
            CLASSIC_BALL = ImageLoader.loadImage("src/main/ressources/balle/blackClassic.png");
            currentImage = CLASSIC_BALL;
        } else if (ball instanceof HyperBall) {
            HYPER_BALL = ImageLoader.loadImage("src/main/ressources/balle/blackHyper.png");
            currentImage = HYPER_BALL;
        } else if (ball instanceof GravityBall) {
            GRAVITY_BALL = ImageLoader.loadImage("src/main/ressources/balle/blackGravity.png");
            currentImage = GRAVITY_BALL;
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/lightPos.png");
            NEGATIF_BALL = ImageLoader.loadImage("src/main/ressources/balle/lightNeg.png");
            currentImage = POSITIF_BALL;
        }
        setImage(currentImage);
        setFitWidth(ball.getRadius() * 2);
        setPreserveRatio(true);
        setSmooth(true);
    }
}
