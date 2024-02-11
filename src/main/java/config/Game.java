package config;

import java.util.Vector;

import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.brick.Brick;
import entity.brick.BrickClassic;
import entity.racket.ClassicRacket;
import entity.racket.Racket;
import geometry.Coordinates;
import utils.GameConstants;

public class Game {

    private Ball ball;
    private Racket racket;
    private Brick[][] bricks;
    private BricksArrangement arrangement;
    private boolean lost = false;
    private int score = 0;
    private int life = 3;

    public Game(Ball ball, Racket racket, BricksArrangement arrangement) {
        this.ball = ball;
        this.racket = racket;
        this.arrangement = arrangement;
        initBricks();
    }

    // Setters/getters
    public Ball getBall() {
        return ball;
    }

    public Racket getRacket() {
        return racket;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getScore() {
        return score;
    }

    public int getLife() {
        return life;
    }

    private void initBricks() {
        switch (arrangement) {
            case DEFAULT:
                initDefaultBricksArrangement();
                break;

            case RANDOM:

                break;
        }
    }

    private void initDefaultBricksArrangement() {

        if (checkDefaultParameters()) {

            bricks = new Brick[GameConstants.MAP_WIDTH][GameConstants.MAP_HEIGHT]; // [colonne][ligne]
            int indexFirstColumn = GameConstants.MAP_WIDTH / GameConstants.COLUMNS_OF_BRICKS;

            for (int i = indexFirstColumn; i < indexFirstColumn + GameConstants.COLUMNS_OF_BRICKS; i++) { // espace côté
                                                                                                          // gauche/droit
                for (int j = 1; j < GameConstants.ROWS_OF_BRICKS + 1; j++) { // 1 espace en haut
                    bricks[i][j] = new BrickClassic(new Coordinates(i * GameConstants.BRICK_WIDTH,
                            j * GameConstants.BRICK_HEIGHT));
                }
            }
        } else {
            throw new IllegalArgumentException("Erreur sur la config de la map.");
        }
    }

    private boolean checkDefaultParameters() {
        return GameConstants.MAP_HEIGHT >= GameConstants.ROWS_OF_BRICKS + 2
                && GameConstants.MAP_WIDTH >= GameConstants.COLUMNS_OF_BRICKS
                && GameConstants.MAP_HEIGHT
                        - GameConstants.ROWS_OF_BRICKS - 2 >= GameConstants.MIN_SPACE_BETWEEN_RACKET_BRICKS;
    }

    public void displayBricksInTerminal() { // pour les tests

        for (int i = 0; i < bricks[0].length; i++) {
            for (int j = 0; j < bricks.length; j++) {
                if (bricks[j][i] != null) {
                    System.out.print("B "); // "B" pour représenter une brique
                } else {
                    System.out.print(". "); // "." pour représenter une case vide
                }
            }
            System.out.println();
        }
    }

    public void createBricks() {
    }

    public void update(long deltaT) {

        // Vérifie si la balle touche une brique
        handleCollisionBricks(); // gérer la collision des briques
        // Si la balle touche la raquette
        if (racket.CollisionRacket(ball)) {
            ball.setCollisionR(true);
            System.out.println("collisionX");
        }
        // Gere les conditions de perte
        if (!ball.movement()) {
            life--;
            ball.reset();
        }
        if (life == 0) {
            lost = true;
        }
        System.out.println("Life :" + life + " lost :" + lost);
    }

    private void handleCollisionBricks() {

        // où se trouve la balle
        int ballBrickX = (int) (ball.getC().getX() / GameConstants.BRICK_WIDTH);
        int ballBrickY = (int) (ball.getC().getY() / GameConstants.BRICK_HEIGHT);

        // Vector newDirection = new Vector(new Coordinates(ball.getDirection().getX(),
        // ball.getDirection().getY()));

        boolean collide = false;

        if (inMap(ballBrickX, ballBrickY - 1) && bricks[ballBrickX][ballBrickY - 1] != null
                && ball.intersectBrick(bricks[ballBrickX][ballBrickY - 1])) { // Brique au dessus
            ball.getDirection().setY(-ball.getDirection().getY());
            collide = true;
        }

        if (inMap(ballBrickX, ballBrickY + 1) && bricks[ballBrickX][ballBrickY + 1] != null
                && ball.intersectBrick(bricks[ballBrickX][ballBrickY + 1])) { // Brique du dessous
            ball.getDirection().setY(-ball.getDirection().getY());
            collide = true;
        }

        if (inMap(ballBrickX - 1, ballBrickY) && bricks[ballBrickX - 1][ballBrickY] != null
                && ball.intersectBrick(bricks[ballBrickX - 1][ballBrickY])) { // Brique à gauche
            ball.getDirection().setY(-ball.getDirection().getY());
            collide = true;
        }

        if (inMap(ballBrickX + 1, ballBrickY) && bricks[ballBrickX + 1][ballBrickY] != null
                && ball.intersectBrick(bricks[ballBrickX + 1][ballBrickY])) { // Brique à droite
            ball.getDirection().setY(-ball.getDirection().getY());
            collide = true;
        }

        if (collide) {
            System.out.println("COGNE");
        }
        // Les 4 if permettent de gérer le cas où la balle arrive pile en diagonale
    }

    private boolean inMap(int x, int y) {
        return x >= 0 && x < GameConstants.MAP_WIDTH && y >= 0 && y < GameConstants.MAP_HEIGHT;
    }

    public void lost() {
        System.exit(0);
    }

    // public boolean collisionRacket(Coordinates c) {
    //     return c.getX() >= racket.getC().getX() && c.getX() <= racket.getC().getX() + racket.getLongueur()
    //             && c.getY() >= racket.getC().getY() && c.getY() <= racket.getC().getY() + racket.getLargeur();
    // }

    public static void main(String[] args) {
        Game g = new Game(new ClassicBall(1), new ClassicRacket(), BricksArrangement.DEFAULT);
        g.displayBricksInTerminal();
    }
}
