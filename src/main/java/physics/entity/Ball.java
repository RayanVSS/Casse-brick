package physics.entity;

import config.Map;
import entity.EntityColor;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import javafx.scene.effect.Light.Point;
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

    public void updatePreview(Set<Brick> bricks) {
        if (this.preview != null) {
            preview.checkCollisionBrick(bricks);
            preview.trajectory();
            preview.add_circle();
            preview.check();
        }
    }

    public void normalizeDirection(){
        if(getDirection().getX() == 0){
            getDirection().setX(1*rotation.getAngle()/90);
        }
        if(getDirection().getY() == 0){
            getDirection().setY(1*rotation.getAngle()/90);
        }
    }

    public boolean intersectBrick(Brick b) {
        return intersectBrick(this.getC(), this.getRadius(), b);
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
       if(checkCollision(this.getC(), this.getDirection(), this.radius,b,rotation)){
           b.absorb(100);
       }
    }

    public static boolean checkCollision(Coordinates c ,Vector d, double radius ,Brick b,Rotation rotation){
        if (intersectBrick(c, (int) radius, b)) {
            for (Segment s : b.getSegments()) {
                if(checkCollision(c,d,radius,s,rotation)){
                    return true;
                }
            }
        }
        return false;
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

    public void checkCollision(Ball ball) {
        if (CollisionB) {
            return;
        }

        double distance = this.radius + ball.radius;
    
        if (Math.abs(this.getX() - ball.getX()) < distance &&
            Math.abs(this.getY() - ball.getY()) < distance) {
    
            double dx = this.getX() - ball.getX();
            double dy = this.getY() - ball.getY();
            distance = Math.sqrt(dx * dx + dy * dy);
    
            if (distance < this.radius + ball.radius) {
                double overlap = this.radius + ball.radius - distance;
    
                double nx = dx / distance; 
                double ny = dy / distance;
    
                this.getC().setX(this.getX() + nx * overlap / 2);
                this.getC().setY(this.getY() + ny * overlap / 2);
                ball.getC().setX(ball.getX() - nx * overlap / 2);
                ball.getC().setY(ball.getY() - ny * overlap / 2);
    
            }

            double angle = Math.atan2(dy, dx);
            double sin = Math.sin(angle);
            double cos = Math.cos(angle);
    
            double tempX, tempY;
            tempX = this.getDirection().getX() * cos + this.getDirection().getY() * sin;
            tempY = this.getDirection().getY() * cos - this.getDirection().getX() * sin;
    
            double vx2 = ball.getDirection().getX() * cos + ball.getDirection().getY() * sin;
            double vy2 = ball.getDirection().getY() * cos - ball.getDirection().getX() * sin;
    
            this.getDirection().setX(vx2 * cos - tempY * sin);
            this.getDirection().setY(tempY * cos + vx2 * sin);
    
            ball.getDirection().setX(tempX * cos - vy2 * sin);
            ball.getDirection().setY(vy2 * cos + tempX * sin);

            CollisionB = true;
            ball.CollisionB = true;
        }
    }

    public boolean checkCollision(Segment segment) {
        return checkCollision(getC(), getDirection(), radius, segment, rotation);
    }

    public static boolean checkCollision(Coordinates c, Vector d, double radius, Segment segment ,Rotation rotation) {
        double x1 = segment.getStart().getX();
        double y1 = segment.getStart().getY();
        double x2 = segment.getEnd().getX();
        double y2 = segment.getEnd().getY();
        double dx = x2 - x1;
        double dy = y2 - y1;
        double len = Math.sqrt(dx * dx + dy * dy);
        dx /= len;
        dy /= len;

        double t = dx * (c.getX() - x1) + dy * (c.getY() - y1);

        //Si le point est proche des rebords du segment
        double closestX, closestY;
        if (t < 0) {
            closestX = x1;
            closestY = y1;
        } else if (t > len) {
            closestX = x2;
            closestY = y2;
        } else {
            closestX = x1 + t * dx;
            closestY = y1 + t * dy;
        }

        double distance = Math.sqrt((c.getX() - closestX) * (c.getX() - closestX) +
                (c.getY() - closestY) * (c.getY() - closestY));

        if (distance <= radius) {
            double normalX = c.getX() - closestX;
            double normalY = c.getY() - closestY;
            double lenNormal = Math.sqrt(normalX * normalX + normalY * normalY);
            normalX /= lenNormal;
            normalY /= lenNormal;

            double d1 = d.getX() * normalX + d.getY() * normalY;
            if(rotation != null){
                //rotation.addEffect(d.getX()/-d.getX()*Math.abs(d.getX())*5);
            }
            d.setX(d.getX()-2 * d1 * normalX);
            d.setY(d.getY()-2 * d1 * normalY);

            return true;
        }
        return false;
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
