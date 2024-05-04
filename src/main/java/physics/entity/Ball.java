package physics.entity;

import config.Map;
import entity.EntityColor;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import physics.geometry.*;
import physics.gui.Preview;
import physics.config.PhysicSetting;
import physics.gui.Preview;
import utils.GameConstants;
import java.util.List;
import java.util.Set;

import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.g;

/**
 * Classe Balle
 * 
 * Classe abstraite qui permet de définir les balles
 * 
 * @version 1.0
 */
public abstract class Ball extends Entity {

    private int radius;
    private double speed;
    private double baseSpeed;
    private EntityColor color;
    private Rotation rotation = new Rotation();
    private PhysicSetting physicSetting = new PhysicSetting();
    private Preview preview;

    // colision avec racket
    public boolean CollisionR = false;
    public boolean CollisionR_Side = false;

    // Collision avec les murs
    private double zoneWidth = GameConstants.DEFAULT_GAME_ROOT_WIDTH;
    private double zoneHeight = GameConstants.DEFAULT_WINDOW_HEIGHT;
    public boolean CollisionB =false;
    private boolean delete = false;

    public Ball() {
        super(new Coordinates(0, 0), new Vector(new Coordinates(0, 0)));
        this.speed = 0;
        this.radius = GameConstants.DEFAULT_BALL_RADIUS;
        this.baseSpeed = GameConstants.DEFAULT_BALL_SPEED;
        physicSetting.setSpeed_Ball(baseSpeed);
        physicSetting.setRadius(radius);
    }

    public Ball(int r) {
        super(new Coordinates(0, 0), new Vector(new Coordinates(0, 0)));
        this.speed = 1;
        this.radius = r;
        physicSetting.setSpeed_Ball(speed);
        physicSetting.setRadius(radius);

    }

    public Ball(Coordinates c, Vector direction, int speed, int d) {
        super(c, direction);
        this.speed = speed;
        this.radius = d;
        this.baseSpeed = GameConstants.DEFAULT_BALL_SPEED;
        physicSetting.setSpeed_Ball(baseSpeed);
        physicSetting.setRadius(radius);
    }

    // Setters/getters

    public double getX() {
        return this.getC().getX();
    }

    public double getY() {
        return this.getC().getY();
    }

    public int getRadius() {
        return this.radius;
    }

    public void setSpeed(double v) {
        this.speed = v;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setRadius(int d) {
        this.radius = d;
    }

    public void setCollisionR(boolean b) {
        CollisionR = b;
    }

    public boolean getCollisionR() {
        return CollisionR;
    }

    public void setSpeed(int v) {
        this.speed = v;
    }

    public PhysicSetting getPhysicSetting() {
        return physicSetting;
    }

    public EntityColor getColor() {
        return color;
    }

    public void setColor(EntityColor color) {
        this.color = color;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setPreview(Preview p) {
        this.preview = p;
    }

    public Preview getPreview() {
        return this.preview;
    }

    public void setDelete(boolean b) {
        delete = b;
    }

    public boolean delete(){
        return delete;
    }

    public void setBaseSpeed(double baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public double getZoneWidth() {
        return zoneWidth;
    }

    public double getZoneHeight() {
        return zoneHeight;
    }

    public void setZoneWidth(double zoneWidth) {
        this.zoneWidth = zoneWidth;
    }

    public void setZoneHeight(double zoneHeight) {
        this.zoneHeight = zoneHeight;
    }

    public void updatePreview(Set<Ball> balls) {
        if (this.preview != null) {
            for(Ball b : balls){
                preview.checkCollisionOtherBall(b);
            }
            preview.trajectory();
            preview.add_circle();
            preview.check();
        }
    }

    public boolean intersectBrick(Brick b) {

        double circleDistance_x = Math.abs(getC().getX() - b.getC().getX() - GameConstants.BRICK_WIDTH / 2);
        double circleDistance_y = Math.abs(getC().getY() - b.getC().getY() - GameConstants.BRICK_HEIGHT / 2);

        if (circleDistance_x > (GameConstants.BRICK_WIDTH / 2 + radius)) {
            return false;
        }
        if (circleDistance_y > (GameConstants.BRICK_HEIGHT / 2 + radius)) {
            return false;
        }

        if (circleDistance_x <= (GameConstants.BRICK_WIDTH / 2)) {
            return true;
        }
        if (circleDistance_y <= (GameConstants.BRICK_HEIGHT / 2)) {
            return true;
        }

        double cornerDistance_sq = (circleDistance_x - GameConstants.BRICK_WIDTH / 2)
                * (circleDistance_x - GameConstants.BRICK_WIDTH / 2)
                + (circleDistance_y - GameConstants.BRICK_HEIGHT / 2)
                        * (circleDistance_y - GameConstants.BRICK_HEIGHT / 2);

        return (cornerDistance_sq <= (radius * radius));
    }

    public static boolean intersectBrick(Coordinates c, int radius, Brick b) {

        double circleDistance_x = Math.abs(c.getX() - b.getC().getX() - GameConstants.BRICK_WIDTH / 2);
        double circleDistance_y = Math.abs(c.getY() - b.getC().getY() - GameConstants.BRICK_HEIGHT / 2);

        if (circleDistance_x > (GameConstants.BRICK_WIDTH / 2 + radius)) {
            return false;
        }
        if (circleDistance_y > (GameConstants.BRICK_HEIGHT / 2 + radius)) {
            return false;
        }

        if (circleDistance_x <= (GameConstants.BRICK_WIDTH / 2)) {
            return true;
        }
        if (circleDistance_y <= (GameConstants.BRICK_HEIGHT / 2)) {
            return true;
        }

        double cornerDistance_sq = (circleDistance_x - GameConstants.BRICK_WIDTH / 2)
                * (circleDistance_x - GameConstants.BRICK_WIDTH / 2)
                + (circleDistance_y - GameConstants.BRICK_HEIGHT / 2)
                        * (circleDistance_y - GameConstants.BRICK_HEIGHT / 2);

        return (cornerDistance_sq <= (radius * radius));
    }


    public abstract void movement(long dt);

    public boolean isOverlap(Racket r) {
        // Trouver le point le plus proche du cercle dans le rectangle
        double closestX = clamp(this.getC().getX(), r.getC().getX(), this.getC().getX() + r.getLargeur());
        double closestY = clamp(this.getC().getY(), r.getC().getY(), this.getC().getY() + r.getLongueur());

        // Calculer la distance entre le centre du cercle et ce point
        double distanceX = this.getC().getX() - closestX;
        double distanceY = this.getC().getY() - closestY;

        // Si la distance est inférieure au rayon du cercle, ils se chevauchent
        return (distanceX * distanceX + distanceY * distanceY) < (GameConstants.DEFAULT_BALL_RADIUS
                * GameConstants.DEFAULT_BALL_RADIUS);
    }

    public boolean isOverlap2(Racket r) {

        double deltaX = this.getC().getX()
                - Math.max(r.getC().getX(), Math.min(this.getC().getX(), r.getC().getX() + r.getLongueur()));
        double deltaY = this.getC().getY()
                - Math.max(r.getC().getY(), Math.min(this.getC().getY(), r.getC().getY() + r.getLargeur()));

        return (deltaX * deltaX + deltaY * deltaY) < (this.getRadius() * this.getRadius());
    }

    public double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public void reset() {
        this.setC(GameConstants.DEFAULT_BALL_START_COORDINATES);
        this.setDirection(GameConstants.DEFAULT_BALL_START_DIRECTION);
        this.setSpeed(GameConstants.DEFAULT_BALL_SPEED);
        this.setRadius(GameConstants.DEFAULT_BALL_RADIUS);
        rotation.stopRotation();
        this.setSpeed(baseSpeed);
    }

    public void checkCollision(Brick b) {
        if (this.intersectBrick(b)) {
            if (this.getC().getX() + this.getRadius() > b.getC().getX()
                    && this.getC().getX() - this.getRadius() < b.getC().getX() + GameConstants.BRICK_WIDTH) {
                this.setDirection(new Vector(new Coordinates(this.getDirection().getX(), -this.getDirection().getY())));
            } else if (this.getC().getY() + this.getRadius() > b.getC().getY()
                    && this.getC().getY() - this.getRadius() < b.getC().getY() + GameConstants.BRICK_HEIGHT) {
                this.setDirection(new Vector(new Coordinates(-this.getDirection().getX(), this.getDirection().getY())));
            } else {
                this.setDirection(
                        new Vector(new Coordinates(-this.getDirection().getX(), -this.getDirection().getY())));
            }
            b.setDestroyed(true);
        }
    }

    public void checkCollision(Set<Brick> bricks) {
        for (Brick b : bricks) {
            this.checkCollision(b);
        }
    }

    public void checkCollisionOtherBall(Set<Ball> balls) {
        if(!CollisionB){
            for(Ball b : balls){
                if(this != b){
                    checkCollision(b);
                }
            }
        }
    }

    public void checkCollisionOtherBall(List<Ball> balls) {
        if(!CollisionB){
            for(Ball b : balls){
                if(!this.equals(b)){
                    checkCollision(b);
                }
            }
        }
    }

    public void checkCollision(Ball b) {
        if(!CollisionB){
            double deltaX = Math.abs(getX() - b.getX());
            double deltaY = Math.abs(getY() - b.getY());
            double distance = deltaX * deltaX + deltaY * deltaY;
            
            if (distance < (getRadius() + b.getRadius()) * (getRadius() + b.getRadius())) {

                double newxSpeed1 = (getDirection().getX() * (4 - 7) + (2 * 7 * b.getDirection().getX())) / 11;

                double newxSpeed2 = (b.getDirection().getX() * (7 - 4) + (2 * 4 * getDirection().getX())) / 11;

                double newySpeed1 = (getDirection().getY() * (4 - 7) + (2 * 7 * b.getDirection().getY())) / 11;

                double newySpeed2 = (b.getDirection().getY() * (7 - 4) + (2 * 4 * getDirection().getY())) / 11;

                b.getDirection().setX(newxSpeed2);
                b.getDirection().setY(newySpeed2);
                getDirection().setX(newxSpeed1);
                getDirection().setY(newySpeed1);

            }
        }
    }
    

    public void checkCollision(Segment s){
        double x1 = s.getStart().getX();
        double y1 = s.getStart().getY();
        double x2 = s.getEnd().getX();
        double y2 = s.getEnd().getY();
        double x = getX();
        double y = getY();
        double dx = x2 - x1;
        double dy = y2 - y1;
        double a = dx * dx + dy * dy;
        double b = 2 * (dx * (x1 - x) + dy * (y1 - y));
        double c = x * x + y * y + x1 * x1 + y1 * y1 - 2 * (x * x1 + y * y1) - radius * radius;
        double delta = b * b - 4 * a * c;
        if (delta >= 0) {
            double t = (-b - Math.sqrt(delta)) / (2 * a);
            double xInt = x1 + t * dx;
            double yInt = y1 + t * dy;
            double nx = xInt - x;
            double ny = yInt - y;
            double n = Math.sqrt(nx * nx + ny * ny);
            nx /= n;
            ny /= n;
            double d = 2 * (getDirection().getX() * nx + getDirection().getY() * ny);
            getDirection().setX(getDirection().getX() - d * nx);
            getDirection().setY(getDirection().getY() - d * ny);
        }
    }

    public double getMass(){
        return physicSetting.getMass();
    }

    public static Ball clone(Ball originalball){
        if (originalball instanceof MagnetBall) {
            return new MagnetBall();
        } else if (originalball instanceof ClassicBall) {
            return new ClassicBall();
        } else if (originalball instanceof HyperBall) {
            return new HyperBall();
        } else if (originalball instanceof GravityBall) {
            return new GravityBall();
        }
        return null;
    }
}
