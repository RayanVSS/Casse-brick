package physics.entity;

import entity.EntityColor;
import physics.config.PhysicSetting;
import physics.geometry.Coordinates;
import physics.geometry.Rotation;
import physics.geometry.Vector;
import physics.gui.Preview;
import utils.GameConstants;

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

    public Ball() {
        super(new Coordinates(0, 0), new Vector(new Coordinates(0, 0)));
        this.speed = 0;
        this.radius = GameConstants.DEFAULT_BALL_RADIUS;
        this.baseSpeed = GameConstants.DEFAULT_BALL_SPEED;
    }

    public Ball(int r) {
        super(new Coordinates(0, 0), new Vector(new Coordinates(0, 0)));
        this.speed = 0;
        this.radius = r;
    }

    public Ball(Coordinates c, Vector direction, int speed, int d) {
        super(c, direction);
        this.speed = speed;
        this.radius = d;
        this.baseSpeed = GameConstants.DEFAULT_BALL_SPEED;
    }

    // Setters/getters

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
        this.setSpeed(baseSpeed);
    }

    public boolean checkCollision(Brick b) {
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
            return true;
        }
        return false;
    }

    public boolean checkCollisionOtherBall(Ball b) {
        // TODO : implement this method
        return false;
    }

}
