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

    private Ball ball;
    private Image classicBall = new Image("/balle/balle1.png");
    private Image hyperBall = new Image("/balle/balleHyper.png");
    private Image gravityBall = new Image("/balle/balleGravity.png");
    private Image positifBall = new Image("/balle/positif.png");
    private Image negatifBall = new Image("/balle/negatif.png");
    private Image redBall = new Image("/balle/balleRouge.png");
    private Image greenBall = new Image("/balle/balleVert.png");
    private Image blueBall = new Image("/balle/balleBleu.png");
    private boolean waitingAdded, waitingRemoved;

    public BallGraphics(Ball ball) {
        this.ball = ball;
        updateImageAndProperties();
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        waitingAdded = true;
    }

    public void update() {
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
        updateImageAndProperties();
    }

    private void updateImageAndProperties() {
        if (ball instanceof ClassicBall)
            setImage(classicBall);
        else if (ball instanceof HyperBall)
            setImage(hyperBall);
        else if (ball instanceof GravityBall)
            setImage(gravityBall);
        else if (ball instanceof MagnetBall) {
            if (((MagnetBall) ball).getEtat().equals("positif"))
                setImage(positifBall);
            else
                setImage(negatifBall);
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
        setFitWidth(ball.getRadius() * 3);
        setFitHeight(ball.getRadius() * 3);
        setPreserveRatio(true);
        setCache(true);
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