package config;

import entity.ball.Ball;
import entity.brick.Brick;
import entity.brick.BrickClassic;
import geometry.Coordinates;
import utils.GameConstants;

public class Map {

    private Brick[][] bricks;
    private BricksArrangement arrangement;

    public Map(BricksArrangement arrangement) {
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

    public Brick[][] getBricks() {
        return bricks;
    }

    private boolean inMap(int x, int y) {
        return x >= 0 && x < bricks.length && y >= 0 && y < bricks[0].length;
    }

    public void handleCollisionBricks(Ball ball) {

        // où se trouve la balle
        int ballBrickX = (int) (ball.getC().getX() / GameConstants.BRICK_WIDTH);
        int ballBrickY = (int) (ball.getC().getY() / GameConstants.BRICK_HEIGHT);

        Brick targetBrick;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (inMap(ballBrickX + i, ballBrickY + j) && bricks[ballBrickX + i][ballBrickY + j] != null) {
                    targetBrick = bricks[ballBrickX + i][ballBrickY + j];
                    if (!targetBrick.isDestroyed() && ball.intersectBrick(targetBrick)) {
                        if (i != 0) {
                            ball.getDirection().setX(-ball.getDirection().getX());
                        } else {
                            ball.getDirection().setY(-ball.getDirection().getY());
                        }
                        targetBrick.setDestroyed(true);
                    }
                }
            }
        }
    }

    public int updateBricksStatus() {
        int count = 0;
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] != null && bricks[i][j].isDestroyed()) {
                    bricks[i][j] = null;
                    Game.score += 10;
                    count++;
                }
            }
        }
        return count ;
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

    
    public static void main(String[] args) {
        Map map = new Map(BricksArrangement.DEFAULT);
        map.displayBricksInTerminal();
    }
}
