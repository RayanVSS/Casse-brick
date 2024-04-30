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
    private List<Ball> ballList;
    private Color COLOR_BONUS = Color.rgb(233, 255, 0);;


    public Bonus(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Bonus(Coordinates c, List<Ball> ballList) {
        super(c.getX(), c.getY(), GameConstants.WIDTH, GameConstants.HEIGHT);
        this.c = c;
        this.ballList=ballList;
        setFill(COLOR_BONUS);
    }

    public static Bonus createBonus(Coordinates c,List<Ball> ballList) {
        if (Math.random() < GameConstants.BONUS_CHANCE) {
            if (Math.random() < 0.3) {
                return new Boost(c);
            }
            else{
                return new Bonus(c,ballList);
            }
        }
        return null;
    }

    public boolean move(Boolean CollisionRacket, Racket racket) {
        if (CollisionRacket) {
            Ball newBall = Ball.clone(ballList.get(0));
            newBall.setC(new Coordinates(racket.getC().getX() + racket.largeur / 2, racket.getC().getY() - newBall.getRadius()));
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
