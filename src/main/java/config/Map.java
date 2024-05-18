package config;

import java.util.ArrayList;
import java.util.Iterator;

import entity.brick.BrickClassic;
import physics.entity.Brick;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class Map {

    // private Brick[][] bricks;
    private ArrayList<Brick> bricks;
    private GameRules rules;
    private int columnsBricks, rowsBricks;

    public Map(GameRules rules, int columnsBricks, int rowsBricks) {
        this.rules = rules;
        this.columnsBricks = columnsBricks;
        this.rowsBricks = rowsBricks;
        initBricks(columnsBricks, rowsBricks);
    }

    private void initBricks(int columnsBricks, int rowsBricks) {
        bricks = new ArrayList<>();
        if (checkDefaultParameters(columnsBricks, rowsBricks)) {
            switch (rules.getArrangement()) {

                case DEFAULT:
                    initDefaultBricksArrangement(columnsBricks, rowsBricks);
                    break;

                case INFINITE:
                    initInfiniteBricksArrangement();
                    break;

                case RANDOM:

                    break;
            }
        } else {
            throw new IllegalArgumentException("Erreur sur la config de la map.");
        }
    }

    private void initInfiniteBricksArrangement() {
        if (checkDefaultParameters(GameConstants.COLUMNS_OF_BRICKS, GameConstants.ROWS_OF_BRICKS)) {
            for (int i = 0; i < GameConstants.MAP_WIDTH; i++) {
                for (int j = 0; j < GameConstants.ROWS_OF_BRICKS; j++) {
                    bricks.add(new BrickClassic(new Coordinates(i * GameConstants.BRICK_WIDTH,
                            j * GameConstants.BRICK_HEIGHT)));
                }
            }
        } else {
            throw new IllegalArgumentException("Erreur sur la config de la map.");
        }
    }

    private void initDefaultBricksArrangement(int columnsBricks, int rowsBricks) {
        int indexFirstColumn = (GameConstants.MAP_WIDTH - columnsBricks) / 2;
        for (int i = indexFirstColumn; i < indexFirstColumn + columnsBricks; i++) { // espace côté
                                                                                    // gauche/droit
            for (int j = 1; j < rowsBricks + 1; j++) { // 1 espace en haut
                bricks.add(new BrickClassic(new Coordinates(i * GameConstants.BRICK_WIDTH,
                        j * GameConstants.BRICK_HEIGHT)));
            }
        }

    }

    private boolean checkDefaultParameters(int columnsBricks, int rowsBricks) {
        return GameConstants.MAP_HEIGHT >= rowsBricks + 2
                && GameConstants.MAP_WIDTH >= columnsBricks
                && GameConstants.MAP_HEIGHT
                        - rowsBricks - 2 >= GameConstants.MIN_SPACE_BETWEEN_RACKET_BRICKS;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public boolean inMap(int x, int y) {
        return x >= 0 && x <=GameConstants.MAP_WIDTH && y >= 0 && y <= GameConstants.MAP_HEIGHT;
    }

    

    // public void handleCollisionBricks(Ball ball, GameRules rules) {

    //     // où se trouve la balle
    //     int ballBrickX = (int) (ball.getC().getX() / GameConstants.BRICK_WIDTH);
    //     int ballBrickY = (int) (ball.getC().getY() / GameConstants.BRICK_HEIGHT);

    //     Brick targetBrick;

    //     for (int i = -1; i <= 1; i++) {
    //         for (int j = -1; j <= 1; j++) {
    //             if (inMap(ballBrickX + i, ballBrickY + j) && bricks[ballBrickX + i][ballBrickY + j] != null) {
    //                 targetBrick = bricks[ballBrickX + i][ballBrickY + j];
    //                 if (!targetBrick.isDestroyed() && ball.intersectBrick(targetBrick)) {
    //                     if (rules.haveBricksCollisionRules()) { // Application des règles du jeu aux collisions
    //                         App.ballSound.update();
    //                         App.ballSound.play();
    //                         handleBricksCollisionRules(targetBrick, ball, rules, i, j);
    //                     } else {
    //                         App.ballSound.update();
    //                         App.ballSound.play();
    //                         handleCollisionDirection(ball, i, j);
    //                         targetBrick.setDestroyed(true);
    //                     }
    //                     return;
    //                 }

    //             }
    //         }
    //     }
    // }

    // private void handleBricksCollisionRules(Brick brick, Ball ball, GameRules rules, int i, int j) {
    //     // Cas où les règles se stackent à corriger
    //     if (!rules.isTransparent() || !brick.isTransparent()) {
    //         handleCollisionDirection(ball, i, j);
    //     }

    //     if ((rules.isColorRestricted() && rules.verifyColor(brick, ball) || !rules.isColorRestricted())
    //             && (rules.isTransparent() && !brick.isTransparent() || !rules.isTransparent())
    //             && (rules.isUnbreakable() && !brick.isUnbreakable() || !rules.isUnbreakable())) {

    //         brick.setDestroyed(true);
    //     }

    //     if (rules.isColorRestricted() && !brick.isTransparent()) {
    //         ball.setColor(brick.getColor());
    //     }
    // }

    // public static void handleCollisionDirection(Ball ball, int i, int j) { // changement directionnel simple en attendant la
    //     // physique plus complexe
    //     if (i != 0)
    //         ball.getDirection().setX(
    //                 -ball.getDirection().getX() + (ball.getRotation().getEffect() / 90) * ball.getDirection().getX());
    //     ball.getRotation().Collision();
    //     if (j != 0)
    //         ball.getDirection().setY(
    //                 -ball.getDirection().getY() + (ball.getRotation().getEffect() / 90) * ball.getDirection().getY());
    //     ball.getRotation().Collision();

    // }

    public boolean updateBricksStatus(Game game) {
        boolean destroyed = false;
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            if (brick != null && brick.isDestroyed()) {
                iterator.remove(); // Utilisation sécurisée de l'itérateur pour supprimer l'élément
                game.setScore(game.getScore() + 10);
                destroyed = true;
            }
        }
        return destroyed;
    }

    public int countBricks() {
        return bricks.size();
    }


    public int getRowsBricks(){
        return rowsBricks;
    }

    public int getColumnsBricks(){
        return columnsBricks;
    }

    

}


    // public Brick lastBricks() {
    //         Brick res = null;
    //         for (int i = bricks.size() - 1; i > -1; i--) {
    //                 if (bricks.get(i) != null) {
    //                     res = bricks.get(i);
    //                     break;
    //                 }
    //             }
    //         return res;
    // }

    // public boolean inMap(int x, int y) {
    //     return x >= 0 && x < bricks.length && y >= 0 && y < bricks[0].length;
    // }
    // public boolean inMap(int x, int y) {
    //     return x >= 0 && x < rowsBricks && y >= 0 && y < columnsBricks;
    // }


// public int lastBrick() {
    //     int res = 0;
    //     for (int i = bricks.length - 1; i > -1; i--) {
    //         for (int j = bricks[0].length - 1; j > -1; j--) {
    //             if (bricks[i][j] != null) {
    //                 res = i;
    //                 break;
    //             }
    //         }
    //     }
    //     return res;
    // }

    // public Brick lastBricks() {
    //     Brick res = null;
    //     for (int i = bricks.length - 1; i > -1; i--) {
    //         for (int j = bricks[0].length - 1; j > -1; j--) {
    //             if (bricks[i][j] != null) {
    //                 res = bricks[i][j];
    //                 break;
    //             }
    //         }
    //     }
    //     return res;
    // }
