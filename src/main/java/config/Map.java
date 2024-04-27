package config;

import config.GameRules.BricksArrangement;
import entity.EntityColor;
import entity.ball.Ball;
import entity.brick.Brick;
import entity.brick.BrickClassic;
import geometry.Coordinates;
import utils.GameConstants;

import java.util.ArrayList;
import java.util.Random;

import org.checkerframework.checker.units.qual.A;

public class Map {

    private Brick[][] bricks;
    private GameRules rules;

    public Map(GameRules rules) {
        this.rules = rules;
        initBricks();
    }

    private void initBricks() {
        switch (rules.getArrangement()) {
            case DEFAULT:
                initDefaultBricksArrangement();
                break;

            case INFINITE:
                initInfiniteBricksArrangement();
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

    // TODO A FACTORISER
    private void initInfiniteBricksArrangement() {
        if (checkDefaultParameters()) {
            bricks = new Brick[GameConstants.MAP_WIDTH][GameConstants.MAP_HEIGHT];
            for (int i = 0; i < GameConstants.MAP_WIDTH; i++) {
                for (int j = 0; j < GameConstants.ROWS_OF_BRICKS; j++) {
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

    public void handleCollisionBricks(Ball ball, GameRules rules) {

        // où se trouve la balle
        int ballBrickX = (int) (ball.getC().getX() / GameConstants.BRICK_WIDTH);
        int ballBrickY = (int) (ball.getC().getY() / GameConstants.BRICK_HEIGHT);

        Brick targetBrick;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (inMap(ballBrickX + i, ballBrickY + j) && bricks[ballBrickX + i][ballBrickY + j] != null) {
                    targetBrick = bricks[ballBrickX + i][ballBrickY + j];
                    if (!targetBrick.isDestroyed() && ball.intersectBrick(targetBrick)) {

                        if (rules.haveBricksCollisionRules()) { // Application des règles du jeu aux collisions
                            handleBricksCollisionRules(targetBrick, ball, rules, i, j);
                        } else {
                            handleCollisionDirection(ball, i, j);
                            targetBrick.setDestroyed(true);
                        }
                        return;
                    }
                }
            }
        }
    }

    private void handleBricksCollisionRules(Brick brick, Ball ball, GameRules rules, int i, int j) {
        // Cas où les règles se stackent à corriger
        if (!rules.isTransparent() || !brick.isTransparent()) {
            handleCollisionDirection(ball, i, j);
        }

        if ((rules.isColorRestricted() && rules.verifyColor(brick, ball) || !rules.isColorRestricted())
                && (rules.isTransparent() && !brick.isTransparent() || !rules.isTransparent())
                && (rules.isUnbreakable() && !brick.isUnbreakable() || !rules.isUnbreakable())) {

            brick.setDestroyed(true);
        }

        if (rules.isColorRestricted() && !brick.isTransparent()) {
            ball.setColor(brick.getColor());
        }
    }

    private void handleCollisionDirection(Ball ball, int i, int j) { // changement directionnel simple en attendant la
                                                                     // physique plus complexe
        if (i != 0)
            ball.getDirection().setX(-ball.getDirection().getX());
        if (j != 0)
            ball.getDirection().setY(-ball.getDirection().getY());
    }

    public boolean updateBricksStatus() {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] != null && bricks[i][j].isDestroyed()) {
                    bricks[i][j] = null;
                    Game.score += 10;
                    return true;
                }
            }
        }
        return false;
    }

    public int countBricks() {
        int count = 0;
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public ArrayList<Brick> getListOfBricks() {
        ArrayList<Brick> list = new ArrayList<>();
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] != null) {
                    list.add(bricks[i][j]);
                }
            }
        }
        return list;
    }
    // TODO méthode :
    // - pour savoir si une brique est arrivé en bas
    // - pour descendre les briques et créer de nouvelle briques FAIT

    public boolean isLost() {
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i][bricks[0].length - 1] != null) {
                return true;
            }
        }
        return false;
    }

    public void infiniteUpdate() {
        ArrayList<Brick> list = getListOfBricks();
    
        for (int i = list.size() - 1; i > -1; i--) {
            Brick b = list.get(i);
            Coordinates c = new Coordinates(b.getC().getX(), b.getC().getY() + 0.60);
            if (inMap(c.getIntX() / GameConstants.BRICK_WIDTH, c.getIntY() / GameConstants.BRICK_HEIGHT)) {
                bricks[(int) b.getC().getX() / GameConstants.BRICK_WIDTH][(int) b.getC().getY()
                        / GameConstants.BRICK_HEIGHT] = null;
                b.setC(c);
                bricks[(int) b.getC().getX() / GameConstants.BRICK_WIDTH][(int) b.getC().getY()
                        / GameConstants.BRICK_HEIGHT] = b;
            }
        }
        for (int ii = 0; ii < bricks.length; ii++) {
            for (int jj = 0; jj < bricks[0].length; jj++) {
                if (bricks[ii][jj] == null) {
                    bricks[ii][jj] = new BrickClassic(
                            new Coordinates(ii * GameConstants.BRICK_WIDTH, jj * GameConstants.BRICK_HEIGHT));
                } else {
                    break;
                }
            }
        }
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
        System.out.println("------------------------------------");
    }

    public void displayColoredBricksInTerminal() { // pour les tests

        for (int i = 0; i < bricks[0].length; i++) {
            for (int j = 0; j < bricks.length; j++) {
                if (bricks[j][i] != null) {
                    switch (bricks[j][i].getColor()) {

                        case RED:
                            System.out.print("R ");
                            break;

                        case GREEN:
                            System.out.print("G ");
                            break;

                        case BLUE:
                            System.out.print("B ");
                            break;
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

    public void displayBricksTransparencyInTerminal() { // pour les tests

        for (int i = 0; i < bricks[0].length; i++) {
            for (int j = 0; j < bricks.length; j++) {
                if (bricks[j][i] != null) {
                    if (bricks[j][i].isTransparent()) {
                        System.out.print("T ");
                    } else {
                        System.out.print("B ");
                    }

                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

}

// int borneXJ = (int) list.get(0).getC().getIntX() / GameConstants.BRICK_WIDTH;
// int borneYI = (int) list.get(0).getC().getIntY() /
// GameConstants.BRICK_HEIGHT;
// for (int i = 0; i < borneYI; i++) {
// for (int j = 0; j < borneXJ; j++) {
// // if (bricks[j][i] == null) {
// bricks[i][j] = new BrickClassic(new Coordinates(i *
// GameConstants.BRICK_WIDTH,
// j * GameConstants.BRICK_HEIGHT));
// // }
// }
// }

// int ii = 0;
// int jj = 0;
// while (bricks[jj][ii] == null) {
// bricks[jj][ii] = new BrickClassic(
// new Coordinates(ii * GameConstants.BRICK_WIDTH, jj *
// GameConstants.BRICK_HEIGHT));
// if (ii == bricks[0].length - 1) {
// jj = 0;
// ii++;
// } else {
// jj++;
// }
// }
// displayBricksInTerminal();