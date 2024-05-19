package entity;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import physics.geometry.Coordinates;
import utils.GameConstants;
import physics.entity.Ball;
import physics.entity.Racket;

public class Bonus extends Rectangle{
    private Coordinates c;
    private Color COLOR_BONUS = Color.rgb(233, 255, 0);;


    public Bonus(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Bonus(Coordinates c) {
        super(c.getX(), c.getY(), GameConstants.WIDTH, GameConstants.HEIGHT);
        this.c = c;
        setFill(COLOR_BONUS);
    }

    public static Bonus createBonus(Coordinates c) {
        double random = Math.random();
        if (random < GameConstants.BONUS_CHANCE) {
            if (random < 0.3) {
                return new Boost(c);
            }
            else{
                return new Bonus(c);
            }
        }
        return null;
    }

    public boolean move(Boolean CollisionRacket, Racket racket, List<Ball> ballList) {
        if (CollisionRacket) {
            Ball newBall = Ball.clone(ballList.get(0));
            newBall.setC(new Coordinates(racket.getC().getX() + racket.largeur / 2, racket.getC().getY() - newBall.getRadius()*2));
            newBall.setDirection(GameConstants.DEFAULT_BALL_START_DIRECTION.clone());
            ballList.add(newBall);
            return true;
        }
        // deplacement du boost
        setY(getY() + GameConstants.BONUS_SPEED);
        this.c.setY(getY());
        return false;
    }

    public Coordinates getC() {
        return c;
    }

    public void setC(Coordinates c) {
        this.c = c;
    }
}
