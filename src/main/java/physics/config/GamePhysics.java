package physics.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BricksGraphics;
import gui.GraphicsFactory.EntityGraphics;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.entity.Entity;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class GamePhysics {

    private ArrayList<Brick> bricks;
    private ArrayList<Ball> balls;
    private Map<Entity, EntityGraphics> entities;
    private boolean paused;

    public GamePhysics() {
        bricks = new ArrayList<>();
        balls = new ArrayList<>();
        entities = new HashMap<>();
        paused = true;
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
        entities.put(brick, new BricksGraphics(brick, 0, 0));
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        entities.put(ball, new BallGraphics(ball));
        ball.setZoneWidth(500);
        ball.setZoneHeight(500);
    }

    public void update() {
        if (!paused) {
            updateBricks();
            updateBalls();
        }
    }

    private void updateBricks() {

    }

    private void updateBalls() {
        for (Ball ball : balls) {
            if (!ball.movement()) {
                // ball.getC().setX(-ball.getC().getX());
                // ball.getC().setY(-ball.getC().getY());
            }
        }
    }

    public void reset() {
        bricks.clear();
        balls.clear();
        entities.clear();
        paused = true;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public Map<Entity, EntityGraphics> getEntities() {
        return entities;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
