package config;

import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.brick.Brick;
import entity.brick.BrickClassic;
import entity.racket.ClassicRacket;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;

public class Game {

    private Ball ball;
    private Racket racket;
    private Brick[][] bricks;
    private BricksArrangement arrangement;

    public Game(Ball ball, Racket racket, BricksArrangement arrangement) {

        this.ball = ball;
        this.racket = racket;
        this.arrangement = arrangement;
        initBricks();
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

            bricks = new Brick[GameConstants.MAP_WIDTH][GameConstants.MAP_HEIGHT]; //[colonne][ligne]
            int indexFirstColumn = GameConstants.MAP_WIDTH / GameConstants.COLUMNS_OF_BRICKS;

            for (int i = indexFirstColumn; i < indexFirstColumn + GameConstants.COLUMNS_OF_BRICKS; i++) { // espace côté gauche/droit
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

    public Ball getBall() {
        return ball;
    }

    public void update(long deltaT) {

        //Vérifie si la balle touche une brique
        handleCollisionBricks(); //gérer la collision des briques

        // Quand la balle arrive au sud on perds (tres primaire comme code)
        if (!ball.movement()) {
            lost();
        }
        // Si la balle touche la raquette
        if (collisionRacket(ball.getC())) {
            ball.setCollisionR(true);
            System.out.println("collisionX");
        }
        checkBricksStatus();
    }

    private void handleCollisionBricks() {

        // où se trouve la balle
        int ballBrickX = (int) (ball.getC().getX() / GameConstants.BRICK_WIDTH);
        int ballBrickY = (int) (ball.getC().getY() / GameConstants.BRICK_HEIGHT);

        Brick targetBrick;

        if (inMap(ballBrickX, ballBrickY - 1) && bricks[ballBrickX][ballBrickY - 1] != null) {

            targetBrick = bricks[ballBrickX][ballBrickY - 1]; //Brique au dessus

            if (ball.intersectBrick(targetBrick)) {
                ball.getDirection().setY(-ball.getDirection().getY());
                targetBrick.setDestroyed(true);
                displayBricksInTerminal();
                // Les setDestroyed true sont temporaires, une gestion plus complexe de leur PV viendra plus tard
            }
        }

        if (inMap(ballBrickX, ballBrickY + 1) && bricks[ballBrickX][ballBrickY + 1] != null) {

            targetBrick = bricks[ballBrickX][ballBrickY + 1]; //Brique du dessous

            if (ball.intersectBrick(targetBrick)) {
                ball.getDirection().setY(-ball.getDirection().getY());
                targetBrick.setDestroyed(true);
                displayBricksInTerminal();
            }
        }

        if (inMap(ballBrickX - 1, ballBrickY) && bricks[ballBrickX - 1][ballBrickY] != null) {

            targetBrick = bricks[ballBrickX - 1][ballBrickY]; //Brique à gauche

            if (ball.intersectBrick(targetBrick)) {
                ball.getDirection().setX(-ball.getDirection().getX());
                targetBrick.setDestroyed(true);
                displayBricksInTerminal();
            }
        }

        if (inMap(ballBrickX + 1, ballBrickY) && bricks[ballBrickX + 1][ballBrickY] != null) {

            targetBrick = bricks[ballBrickX + 1][ballBrickY]; //Brique à droite
            if (ball.intersectBrick(targetBrick)) {
                ball.getDirection().setX(-ball.getDirection().getX());
                targetBrick.setDestroyed(true);
                displayBricksInTerminal();
            }
        }
        //Les 4 if permettent de gérer le cas où la balle arrive pile en diagonale
    }

    private boolean inMap(int x, int y) {
        return x >= 0 && x < GameConstants.MAP_WIDTH && y >= 0 && y < GameConstants.MAP_HEIGHT;
    }

    private void checkBricksStatus() {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] != null && bricks[i][j].isDestroyed()) {
                    bricks[i][j] = null;
                }
            }
        }
    }

    public void lost() {
        System.exit(0);
    }

    public Racket getRacket() {
        return racket;
    }

    public boolean collisionRacket(Coordinates c) {
        return c.getX() >= racket.getC().getX() && c.getX() <= racket.getC().getX() + racket.getLongueur()
                && c.getY() >= racket.getC().getY() && c.getY() <= racket.getC().getY() + racket.getLargeur();
    }

    public Brick[][] getMap() {
        return bricks;
    }

    // public static void main(String[] args) {
    //     Game g = new Game(new ClassicBall(1), new ClassicRacket(), BricksArrangement.DEFAULT);
    //     g.displayBricksInTerminal();
    // }
}
