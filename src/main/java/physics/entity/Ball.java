package physics.entity;

import entity.EntityColor;
import entity.brick.Brick;
import physics.geometry.*;
import physics.gui.Preview;
import physics.config.PhysicSetting;
import utils.GameConstants;

/**
 * Classe Balle
 * 
 * Classe abstraite qui permet de définir les balles
 * 
 * @version 1.0
 */
public abstract class Ball {

    private Coordinates c;
    private Vector direction;
    private int radius;
    private double speed;
    private EntityColor color;
    private Rotation rotation = new Rotation();
    private PhysicSetting physicSetting = new PhysicSetting();
    private Preview preview;

    // colision avec racket
    public boolean CollisionR = false;
    public boolean CollisionR_Side = false;
    public boolean CollisionB =false;

    public Ball() {
        c=new Coordinates(0, 0);
        this.direction = new Vector(new Coordinates(0, 0));
        this.speed = 0;
        this.radius = GameConstants.DEFAULT_BALL_RADIUS;
    }

    public Ball(int r) {
        c=new Coordinates(0, 0);
        this.direction = new Vector(new Coordinates(0, 0));
        this.speed = 0;
        this.radius = r;
    }

    public Ball(Coordinates c, Vector direction, int speed, int d) {
        this.c=c;
        this.direction = direction;
        this.speed = speed;
        this.radius = d;
    }

    // Setters/getters

    public Coordinates getC() {
        return c;
    }

    public void setC(Coordinates c) {
        this.c = c;
    }

    public int getRadius() {
        return this.radius;
    }

    public Vector getDirection() {
        return this.direction;
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

    public void setDirection(Vector d) {
        this.direction = d;
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

    public void updatePreview() {
        if (this.preview != null) {
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

    public abstract boolean movement();

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
    }

    public boolean checkCollision(Brick b) {
        if (this.intersectBrick(b)) {
            if (this.getC().getX() + this.getRadius() > b.getC().getX() && this.getC().getX() - this.getRadius() < b.getC().getX() + GameConstants.BRICK_WIDTH) {
                this.setDirection(new Vector(new Coordinates(this.getDirection().getX(), -this.getDirection().getY())));
            } else if (this.getC().getY() + this.getRadius() > b.getC().getY() && this.getC().getY() - this.getRadius() < b.getC().getY() + GameConstants.BRICK_HEIGHT) {
                this.setDirection(new Vector(new Coordinates(-this.getDirection().getX(), this.getDirection().getY())));
            } else {
                this.setDirection(new Vector(new Coordinates(-this.getDirection().getX(), -this.getDirection().getY())));
            }
            return true;
        }
        return false;
    }

    public void checkCollisionOtherBall(Ball b){
        // TODO : implement this method
        if(!CollisionB){
            if(c.getX()>=b.getC().getX()-radius && c.getX()<=b.getC().getX()+radius){
                b.getDirection().setX(-b.getDirection().getX()+(b.getRotation().getEffect())/90*b.getDirection().getX());
                direction.setX(-direction.getX()+(b.getRotation().getEffect())/90*direction.getX());
                CollisionB=true;
                b.CollisionB=true;
            } 
            if(c.getY()>=b.getC().getY()-radius && c.getY()<=b.getC().getY()+radius){
                b.getDirection().setY(-b.getDirection().getY()+(b.getRotation().getEffect())/90*b.getDirection().getY());
                direction.setY(-direction.getY()+(b.getRotation().getEffect())/90*direction.getY());
                CollisionB=true;
                b.CollisionB=true;
            }
        }
        
    }

}
