package physics.config;

import java.util.ArrayList;
import java.util.Map;

import config.Game;
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
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
        entities.put(brick, new EntityGraphics());
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        entities.put(ball, new EntityGraphics());
    }

    public void update() {
        updateBricks();
        updateBalls();
    }

    private void updateBricks() {

    }

    private void updateBalls() {

    }
}
