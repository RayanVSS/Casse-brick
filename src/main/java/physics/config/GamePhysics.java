package physics.config;

import java.util.ArrayList;

import physics.entity.Ball;
import physics.entity.Brick;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class GamePhysics {

    private ArrayList<Brick> bricks;
    private ArrayList<Ball> balls;

    public GamePhysics() {
        bricks = new ArrayList<>();
        balls = new ArrayList<>();
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void update() {

    }
}
