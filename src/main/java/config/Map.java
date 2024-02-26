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
                            if (i != 0) { // changement directionnel simple en attendant la physique plus complexe
                                ball.getDirection().setX(-ball.getDirection().getX());
                            }
                            if (j != 0) {
                                ball.getDirection().setY(-ball.getDirection().getY());
                            }
                            targetBrick.setDestroyed(true);
                        }
                    }
                }
            }
        }
    }

    private void handleBricksCollisionRules(Brick brick, Ball ball, GameRules rules, int i, int j) {

        if (rules.isTransparent()) {
            if (brick.isTransparent()) {
                if (i != 0) {
                    ball.getDirection().setX(-ball.getDirection().getX());
                }
                if (j != 0) {
                    ball.getDirection().setY(-ball.getDirection().getY());
                }
            } else {
                brick.setDestroyed(true);
            }

        } else {
            if (i != 0) { // changement directionnel simple en attendant la physique plus complexe
                ball.getDirection().setX(-ball.getDirection().getX());
            }
            if (j != 0) {
                ball.getDirection().setY(-ball.getDirection().getY());
            }
            if (rules.isColorRestricted()) {
                if (rules.verifyColor(brick, ball)) {
                    System.out.println(rules.verifyColor(brick, ball));
                    if (rules.isUnbreakable() && !brick.isUnbreakable() || !rules.isUnbreakable()) {
                        brick.setDestroyed(true);
                    }
                }
                ball.setColor(brick.getColor());
            } else {
                if (rules.isUnbreakable() && !brick.isUnbreakable()) {
                    brick.setDestroyed(true);
                }
            }

        }
    }

    public void updateBricksStatus() {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] != null && bricks[i][j].isDestroyed()) {
                    bricks[i][j] = null;
                }
            }
        }
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
