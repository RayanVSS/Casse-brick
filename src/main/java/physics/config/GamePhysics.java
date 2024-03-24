package physics.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import config.Game;
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

    public GamePhysics() {
        bricks = new ArrayList<>();
        balls = new ArrayList<>();
        entities = new HashMap<>();
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
        entities.put(brick, new BricksGraphics(brick, 0, 0));
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        entities.put(ball, new BallGraphics(ball));
    }

    public void update() {
        updateBricks();
        updateBalls();
    }

    private void updateBricks() {

    }

    private void updateBalls() {

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

}
