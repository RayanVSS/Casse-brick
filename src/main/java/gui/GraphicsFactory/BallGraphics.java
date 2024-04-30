package gui.GraphicsFactory;

import utils.GameConstants;
import entity.ball.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import physics.entity.Ball;
import physics.entity.Entity;

public class BallGraphics extends Circle implements EntityGraphics {
    private Color color;
    private Color MAGNET_POS;
    private Color MAGNET_NEG;
    private Color GRAVITY;
    private Color HYPER;

    private Ball ball;

    private boolean waitingAdded, waitingRemoved;
    // Partie pour le drag and drop
    private boolean isMouseDraggingBall = false;
    private double mouseX, mouseY = 0;
    private Image image;
    private ImagePattern ballImage;
    
    public BallGraphics(Ball ball) {
        super(ball.getRadius());
        this.ball = ball;
        setBall();
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
        waitingAdded = true;
    }

    public BallGraphics(Image i, Ball ball) {
        this.ball = ball;
        // setImage(i);
        // currentImage = i;
        // if (i == null) {
        //     currentImage = new Image(physicBall);
        //     setImage(currentImage);
        // }
        // (ball.getRadius() / 3);
        // setFitHeight(ball.getRadius() / 3);
        // setX(ball.getC().getX());
        // setY(ball.getC().getY());
        setScaleX(ball.getRadius() / 2);
        setScaleY(ball.getRadius() / 2);
        waitingAdded = true;
    }

    public void update() {
        updateBall();
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
    }

    private void setBall() {
        color();
        this.setFill(color);
        if (ball instanceof GravityBall) {
            this.setStrokeWidth(3.5);
            this.setStroke(GRAVITY);
        } else if (ball instanceof HyperBall) {
            this.setStrokeWidth(3.5);
            this.setStroke(HYPER);
        }
    }

    private void color() {
        switch (GameConstants.CSS) {
            // TODO: trouver les bonnes couleurs pour gravity et hyper
            case PINK:
                color = Color.rgb(199, 21, 133);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);

                break;
            case CLASSIC:
                color = Color.rgb(39, 54, 84);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);
                break;
            case LIGHT:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);
                break;
            case BLACK:
                color = Color.rgb(245, 245, 245);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);
                break;
            case ACHROMATOPSIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(130, 130, 130);
                MAGNET_NEG = Color.rgb(40, 40, 40);
                break;
            case DEUTERANOPIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 246, 233);
                MAGNET_NEG = Color.rgb(137, 104, 29);
                break;
            case PROTANOPIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 247, 216);
                MAGNET_NEG = Color.rgb(123, 110, 0);
                break;
            case TRITANOPIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 244, 249);
                MAGNET_NEG = Color.rgb(54, 118, 129);
                break;
            default:
                break;
        }
        GRAVITY = Color.rgb(255, 16, 240);
        HYPER = Color.rgb(0, 0, 255);
    }

    private void updateBall() {
        if (ball instanceof MagnetBall) {
            if (((MagnetBall) ball).getEtat().equals("positif")) {
                this.setFill(MAGNET_POS);
            } else {
                this.setFill(MAGNET_NEG);
            }
        }
        if (ball.getColor() != null) {
            switch (ball.getColor()) {
                case RED:
                    this.setFill(Color.RED);
                    break;
                case GREEN:
                    this.setFill(Color.GREEN);
                    break;
                case BLUE:
                    this.setFill(Color.BLUE);
                    break;
                default:
                    break;
            }
        }
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

    // private void setImageBasedOnCSS(Theme css) {
    // String imagePath = IMAGE_PATH + css.getName().toLowerCase() + "/";
    // if (ball instanceof ClassicBall) {
    // setImage(imagePath + "classic.png");
    // } else if (ball instanceof HyperBall) {
    // setImage(imagePath + "hyper.png");
    // } else if (ball instanceof GravityBall) {
    // setImage(imagePath + "gravity.png");
    // } else if (ball instanceof MagnetBall) {
    // setImage(imagePath + ((MagnetBall) ball).getEtat().equals("positif") != null
    // ? POSITIF_BALL : NEGATIF_BALL);
    // }
    // }
    // if (ball instanceof MagnetBall) {
    // String newImagePath = ((MagnetBall) ball).getEtat().equals("positif") ?
    // POSITIF_BALL : NEGATIF_BALL;
    // setImage(newImagePath);
    // }
    // if (ball.getColor() != null) {
    // switch (ball.getColor()) {
    // case RED:
    // setImage(RED_BALL);
    // break;
    // case GREEN:
    // setImage(GREEN_BALL);
    // break;
    // case BLUE:
    // setImage(BLUE_BALL);
    // break;
    // default:
    // break;
    // }
    // }

    // private void setImage(String imagePath) {
    // Image newImage = ImageLoader.loadImage(imagePath);
    // if (newImage != currentImage) {
    // currentImage = newImage;
    // ImagePattern imagePattern = new ImagePattern(currentImage);
    // this.setFill(imagePattern);
    // }
    // }

    // private static final String IMAGE_PATH = "src/main/ressources/balle/";
    // private static final String POSITIF_BALL = "positif.png";
    // private static final String NEGATIF_BALL = "negatif.png";
    // private static final String RED_BALL = IMAGE_PATH + "red.png";
    // private static final String GREEN_BALL = IMAGE_PATH + "green.png";
    // private static final String BLUE_BALL = IMAGE_PATH + "blue.png";
    // private Image currentImage;
}