package physics.entity;

import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import physics.geometry.*;
import physics.gui.Preview;
import physics.config.PhysicSetting;
import utils.GameConstants;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

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

    public boolean CollisionR = false;
    // Collision avec les murs
    private double zoneWidth = GameConstants.DEFAULT_GAME_ROOT_WIDTH;
    private double zoneHeight = GameConstants.DEFAULT_WINDOW_HEIGHT;
    public boolean CollisionB = false;
    private boolean delete = false;
    public static Racket getRa; // coordonnées de la raquette

    private double newX = GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2;
    private double newY = GameConstants.DEFAULT_WINDOW_HEIGHT / 2;

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

    public void setNewCoordinates(double x, double y) {
        this.newX = x;
        this.newY = y;
    }

    public void setNewCoordinates(){
        this.getC().setX(newX);
        this.getC().setY(newY);
    }

    public Coordinates getNewCoordinates() {
        return new Coordinates(newX, newY);
    }


    public double getNewX() {
        return newX;
    }

    public double getNewY() {
        return newY;
    }

    public void setNewX(double newX) {
        this.newX = newX;
    }

    public void setNewY(double newY) {
        this.newY = newY;
    }
   
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

    public boolean delete() {
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

    public void normalizeDirection() {
        if (getDirection().getX() == 0) {
            getDirection().setX(1 * rotation.getAngle() / 90);
        }
        if (getDirection().getY() == 0) {
            getDirection().setY(1 * rotation.getAngle() / 90);
        }
        if(getDirection().getX()>=8){
            getDirection().setX(8);
        }
        if(getDirection().getY()>=8){
            getDirection().setY(8);
        }
    }

    public boolean intersectBrick(Brick b) {
        return PhysicTools.intersectBrick(this.getC(), this.getRadius(), b);
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

    public void reset(Coordinates c) {
        this.setC(c);
        // this.setC(GameConstants.DEFAULT_BALL_START_COORDINATES);
        this.setDirection(GameConstants.DEFAULT_BALL_START_DIRECTION);
        this.setSpeed(GameConstants.DEFAULT_BALL_SPEED);
        this.setRadius(GameConstants.DEFAULT_BALL_RADIUS);
        rotation.stopRotation();
        this.setSpeed(baseSpeed);
    }

    public boolean checkCollision(Brick b) {
        if (PhysicTools.checkCollision(getNewCoordinates(),getC(), this.getDirection(), this.radius, b, rotation)) {
            return true;
        }
        return false;
    }

    public void checkCollision(Set<Brick> bricks, boolean autoDestroy) {
        for (Brick b : bricks) {
            boolean destroy = checkCollision(b);
            if (autoDestroy && destroy) {
                b.absorb(100);
            }
        }
    }

    public void checkCollision(ArrayList<Brick> bricks) {
        for (Brick b : bricks) {
            this.checkCollision(b);
        }
    }

    public void checkCollisionOtherBall(Set<Ball> balls) {
        if (!CollisionB) {
            for (Ball b : balls) {
                if (this != b) {
                    checkCollision(b);
                }
            }
        }
    }

    public void checkCollisionOtherBall(List<Ball> balls) {
        if (!CollisionB) {
            for (Ball b : balls) {
                if (!this.equals(b)) {
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
        double dx = this.getX() - ball.getX();
        double dy = this.getY() - ball.getY();

        if (Math.abs(dx) < distance &&
                Math.abs(dy) < distance) {

            distance = Math.sqrt(dx * dx + dy * dy);

            if (distance <= this.radius + ball.radius) {
                double overlap = this.radius + ball.radius - distance;

                double nx = dx / distance;
                double ny = dy / distance;

                this.getC().setX(this.getX() + nx * overlap / 2);
                this.getC().setY(this.getY() + ny * overlap / 2);
                ball.getC().setX(ball.getX() - nx * overlap / 2);
                ball.getC().setY(ball.getY() - ny * overlap / 2);

                double angle = Math.atan2(dy, dx);
                double sin = Math.sin(angle);
                double cos = Math.cos(angle);

                double vx1 = this.getDirection().getX() * cos + this.getDirection().getY() * sin;
                double vy1 = this.getDirection().getY() * cos - this.getDirection().getX() * sin;

                double vx2 = ball.getDirection().getX() * cos + ball.getDirection().getY() * sin;
                double vy2 = ball.getDirection().getY() * cos - ball.getDirection().getX() * sin;

                this.getDirection().setX(vx2 * cos - vy1 * sin);
                this.getDirection().setY(vy1 * cos + vx2 * sin);

                ball.getDirection().setX(vx1 * cos - vy2 * sin);
                ball.getDirection().setY(vy2 * cos + vx1 * sin);

                CollisionB = true;
                ball.CollisionB = true;
            }
        }
    }

    public boolean checkCollision(Segment segment) {
        return PhysicTools.checkCollision(getNewCoordinates(),getC(), getDirection(), radius, segment, rotation);
    }

    public boolean checkCollision(Racket r) {
        for (Segment l : r.segments) {
            if (checkCollision(l)) {
                this.CollisionR = true;
                this.rotation.addEffect(-(r.getDirection().getX() * r.speed));
                return true;
            }
        }
        return false;
    }

    public double getMass() {
        return physicSetting.getMass();
    }

    public static Ball clone(Ball originalball) {
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
