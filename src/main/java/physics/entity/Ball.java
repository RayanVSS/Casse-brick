package physics.entity;

import entity.EntityColor;
import entity.ball.ClassicBall;
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
    private double baseSpeed;
    private EntityColor color;
    private Rotation rotation = new Rotation();
    private PhysicSetting physicSetting = new PhysicSetting();
    private Preview preview;

    // colision avec racket
    public boolean CollisionR = false;
    public boolean CollisionR_Side = false;
    public boolean CollisionB =false;

    public Ball() {
        c = new Coordinates(0, 0);
        this.direction = new Vector(new Coordinates(0, 0));
        this.speed = 0;
        this.radius = GameConstants.DEFAULT_BALL_RADIUS;
        this.baseSpeed = GameConstants.DEFAULT_BALL_SPEED;
        physicSetting.setSpeed_Ball(baseSpeed);
        physicSetting.setRadius(radius);
    }

    public Ball(int r) {
        c = new Coordinates(0, 0);
        this.direction = new Vector(new Coordinates(0, 0));
        this.speed = 1;
        this.radius = r;
        physicSetting.setSpeed_Ball(speed);
        physicSetting.setRadius(radius);

    }

    public Ball(Coordinates c, Vector direction, int speed, int d) {
        this.c = c;
        this.direction = direction;
        this.speed = speed;
        this.radius = d;
        this.baseSpeed = GameConstants.DEFAULT_BALL_SPEED;
        physicSetting.setSpeed_Ball(baseSpeed);
        physicSetting.setRadius(radius);
    }

    // Setters/getters

    public Coordinates getC() {
        return c;
    }

    public void setC(Coordinates c) {
        this.c = c;
    }

    public double getX() {
        return c.getX();
    }

    public double getY() {
        return c.getY();
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

    public void setBaseSpeed(double baseSpeed) {
        this.baseSpeed = baseSpeed;
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

    public void checkCollisionOtherBall(Ball b) {
        if (!CollisionB) {
            // gestion de la collision entre les balles en X
            boolean collisionX1 = c.getX()+radius>=b.getC().getX()-b.getRadius() && c.getX()-radius<b.getC().getX()+b.getRadius();
            boolean collisionX2 = b.getX()+b.getRadius()>=getX()-radius && b.getX()-b.getRadius()<getX()+radius;
            boolean collisionXY = c.getY()+radius<=Math.max(c.getY()+radius,b.getY()+b.getRadius()) && c.getY()-radius>=Math.min(c.getY()-radius,b.getY()-b.getRadius()) && b.getY()+b.getRadius()<=Math.max(c.getY()+radius,b.getY()+b.getRadius()) && b.getY()-b.getRadius()>=Math.min(c.getY()-radius,b.getY()-b.getRadius());
            // gestion de la collision entre les balles en Y
            boolean collisionY1 = c.getY()+radius>=b.getC().getY()-b.getRadius() && c.getY()-radius<b.getC().getY()+b.getRadius() ;
            boolean collisionY2 = b.getY()+b.getRadius()>=getY()-radius && b.getY()-b.getRadius()<getY()+radius ;
            boolean collisionYX = c.getX()+radius<=Math.max(c.getX()+radius,b.getX()+b.getRadius()) && c.getX()-radius>=Math.min(c.getX()-radius,b.getX()-b.getRadius()) && b.getX()+b.getRadius()<=Math.max(c.getX()+radius,b.getX()+b.getRadius()) && b.getX()-b.getRadius()>=Math.min(c.getX()-radius,b.getX()-b.getRadius());
            if(collisionX1||collisionX2 && collisionXY){
                if(direction.getX() > 0 && b.getDirection().getX() < 0){
                    direction.setX(-b.getDirection().getX());
                    b.getDirection().setX(-direction.getX());
                }
                else if(direction.getX() > 0 && b.getDirection().getX() > 0){
                    direction.setX(-direction.getX()+b.getDirection().getX());
                    b.getDirection().setX(Math.max(direction.getX(), b.getDirection().getX()));
                }
                else if(direction.getX() < 0 && b.getDirection().getX() < 0){
                    direction.setX(Math.min(direction.getX(), b.getDirection().getX()));
                    b.getDirection().setX(-b.getDirection().getX()+direction.getX());
                }
                CollisionB = true;
                b.CollisionB=true;
            }
            else if(collisionY1 || collisionY2 && collisionYX){
                if(direction.getY() > 0 && b.getDirection().getY() < 0){
                    direction.setY(-b.getDirection().getY());
                    b.getDirection().setY(-direction.getY());
                }
                else if(direction.getY() > 0 && b.getDirection().getY() > 0){
                    direction.setY(-direction.getY()+b.getDirection().getY());
                    b.getDirection().setY(Math.max(direction.getY(), b.getDirection().getY()));
                }
                else if(direction.getY() < 0 && b.getDirection().getY() < 0){
                    direction.setY(Math.min(direction.getY(), b.getDirection().getY()));
                    b.getDirection().setY(-b.getDirection().getY()+direction.getY());
                }
                CollisionB = true;
                b.CollisionB=true;
            }
        }
    }
    
    public void checkCollision(Segment s){
        double x1 = s.getStart().getX();
        double y1 = s.getStart().getY();
        double x2 = s.getEnd().getX();
        double y2 = s.getEnd().getY();
        double x = c.getX();
        double y = c.getY();
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
            double d = 2 * (direction.getX() * nx + direction.getY() * ny);
            direction.setX(direction.getX() - d * nx);
            direction.setY(direction.getY() - d * ny);
        }
    }
}
