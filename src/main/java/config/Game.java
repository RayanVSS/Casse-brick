package config;

import java.util.Vector;

import entity.ball.Ball;
import entity.brick.Brick;
import entity.brick.BrickClassic;
import entity.racket.Racket;
import geometry.Coordinates;
import utils.GameConstants;

public class Game {

    private Ball ball;
    private Racket racket;
    private Brick[][] bricks;
    private BricksArrangement arrangement;
    private boolean lost = false;

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
            bricks = new Brick[GameConstants.MAP_HEIGHT][GameConstants.MAP_WIDTH]; // [ligne][colonne]
            for (int i = 1; i < GameConstants.ROWS_OF_BRICKS + 1; i++) { // 1 espace en haut
                for (int j = 1; j < GameConstants.COLUMNS_OF_BRICKS + 1; j++) { // 1 espace côté gauche/droit
                    bricks[i][j] = new BrickClassic(new Coordinates(i, j));
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

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (bricks[i][j] != null) {
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
        // Quand la balle arrive au sud on perds (tres primaire comme code)
        if (!ball.movement()) {
            lost = true;
            System.out.println("Perdu");
        }
        // Si la balle touche la raquette
        if (racket.CollisionRacket(ball)) {
            ball.setCollisionR(true);
            //garder la balle au dessus de la raquette pour eviter que la balle traverse la raquette
            // ball.getC().setY(racket.getC().getY() - ball.getRadius());
            System.out.println("collision");
        }
    }

    // public static void main(String[] args) {
    // Game g = new Game(new Ball(), new Racket(), BricksArrangement.DEFAULT);
    // g.displayBricksInTerminal();
    // }
}
