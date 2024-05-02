package gui.GraphicsFactory;

import utils.GameConstants;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import physics.entity.Ball;
import physics.entity.Entity;

public class BallGraphics extends ImageView implements EntityGraphics {
    private static String POSITIF_BALL;
    private static String NEGATIF_BALL;
    private static String RED_BALL = ("src/main/ressources/balle/red.png");
    private static String GREEN_BALL = ("src/main/ressources/balle/green.png");
    private static String BLUE_BALL = ("src/main/ressources/balle/blue.png");

    private Ball ball;

    //Partie pour le drag and drop
    private boolean isMouseDraggingBall = false;
    private double mouseX , mouseY = 0;
 
    private Image currentImage;
    private boolean waitingAdded, waitingRemoved;

    public BallGraphics(Ball ball) {
        this.ball = ball;
        setImageAndProperties();
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        waitingAdded = true;
    }

    public BallGraphics(String imagePath, Ball ball) {
        this.ball = ball;
        setImage(imagePath, currentImage);
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        waitingAdded = true;
    }

    public void update() {
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        updateImageAndProperties();
        setRotate(getRotate()+ball.getRotation().getAngle()/10);
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
            Image newImage = null;
            setImage((((MagnetBall) ball).getEtat().equals("positif") ? POSITIF_BALL : NEGATIF_BALL), newImage);
            if (newImage != currentImage) {
                currentImage = newImage;
                setImage(currentImage);
            }
        }
        if (ball.getColor() != null) {
            Image newImage = null;
            switch (ball.getColor()) {
                case RED:
                    setImage(RED_BALL, newImage);
                    break;
                case GREEN:
                    setImage(GREEN_BALL, newImage);
                    break;
                case BLUE:
                    setImage(BLUE_BALL, newImage);
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
            setImage("src/main/ressources/balle/pink/classic.png", currentImage);
        } else if (ball instanceof HyperBall) {
            setImage("src/main/ressources/balle/pink/hyper.png", currentImage);
        } else if (ball instanceof GravityBall) {
            setImage("src/main/ressources/balle/pink/gravity.png", currentImage);
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ("src/main/ressources/balle/pink/positif.png");
            NEGATIF_BALL = ("src/main/ressources/balle/pink/negatif.png");
            setImage(POSITIF_BALL, currentImage);
        }
    }

    private void setClassicImage() {
        if (ball instanceof ClassicBall) {
            setImage("src/main/ressources/balle/classic/classic.png", currentImage);
        } else if (ball instanceof HyperBall) {
            setImage("src/main/ressources/balle/classic/hyper.png", currentImage);
        } else if (ball instanceof GravityBall) {
            setImage("src/main/ressources/balle/classic/gravity.png", currentImage);
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ("src/main/ressources/balle/classic/positif.png");
            NEGATIF_BALL = ("src/main/ressources/balle/classic/negatif.png");
            setImage(POSITIF_BALL, currentImage);
        }
    }

    private void setLightImage() {
        if (ball instanceof ClassicBall) {
            setImage("src/main/ressources/balle/light/classic.png", currentImage);
        } else if (ball instanceof HyperBall) {
            setImage("src/main/ressources/balle/light/hyper.png", currentImage);
        } else if (ball instanceof GravityBall) {
            setImage("src/main/ressources/balle/light/gravity.png", currentImage);
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ("src/main/ressources/balle/light/positif.png");
            NEGATIF_BALL = ("src/main/ressources/balle/light/negatif.png");
            setImage(POSITIF_BALL, currentImage);
        }
    }

    private void setBlackImage() {
        if (ball instanceof ClassicBall) {
            setImage("src/main/ressources/balle/black/classic.png", currentImage);
        } else if (ball instanceof HyperBall) {
            setImage("src/main/ressources/balle/black/hyper.png", currentImage);
        } else if (ball instanceof GravityBall) {
            setImage("src/main/ressources/balle/black/gravity.png", currentImage);
        } else if (ball instanceof MagnetBall) {
            POSITIF_BALL = ("src/main/ressources/balle/black/positif.png");
            NEGATIF_BALL = ("src/main/ressources/balle/black/negatif.png");
            setImage(POSITIF_BALL, currentImage);
        }
    }

    private void setImage(String imagePath, Image image) {
        image = ImageLoader.loadImage(imagePath);
        setImage(image);
        setFitWidth(ball.getRadius() * 2);
        setPreserveRatio(true);
        setSmooth(true);
    }

   public boolean IsMouseDraggingBall() {
        return isMouseDraggingBall;
    }

    public void setMouseDraggingBall(boolean mouseDraggingBall) {
        isMouseDraggingBall = mouseDraggingBall;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public void setMouseXY(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
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
