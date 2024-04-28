package config;

import entity.brick.BrickClassic;
import gui.App;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.geometry.Coordinates;
import utils.GameConstants;

import java.util.ArrayList;

public class Map {

    private Brick[][] bricks;
    private GameRules rules;
    private Game game;

    public Map(GameRules rules, int columnsBricks, int rowsBricks,Game game ) {
        this.rules = rules;
        this.game = game;
        initBricks(columnsBricks, rowsBricks);
    }

    private void initBricks(int columnsBricks, int rowsBricks) {
        if (checkDefaultParameters(columnsBricks, rowsBricks)) {
            switch (rules.getArrangement()) {
                case DEFAULT:
                    initDefaultBricksArrangement(columnsBricks, rowsBricks);
                    break;

                case RANDOM:

                    break;
            }
        } else {
            throw new IllegalArgumentException("Erreur sur la config de la map.");
        }
    }

    private void initDefaultBricksArrangement(int columnsBricks, int rowsBricks) {

        bricks = new Brick[GameConstants.MAP_WIDTH][GameConstants.MAP_HEIGHT]; // [colonne][ligne]
        int indexFirstColumn = (GameConstants.MAP_WIDTH - columnsBricks) / 2;

        for (int i = indexFirstColumn; i < indexFirstColumn + columnsBricks; i++) { // espace côté
                                                                                    // gauche/droit
            for (int j = 1; j < rowsBricks + 1; j++) { // 1 espace en haut
                bricks[i][j] = new BrickClassic(new Coordinates(i * GameConstants.BRICK_WIDTH,
                        j * GameConstants.BRICK_HEIGHT));
            }
        }

    }

    private boolean checkDefaultParameters(int columnsBricks, int rowsBricks) {
        return GameConstants.MAP_HEIGHT >= rowsBricks + 2
                && GameConstants.MAP_WIDTH >= columnsBricks
                && GameConstants.MAP_HEIGHT
                        - rowsBricks - 2 >= GameConstants.MIN_SPACE_BETWEEN_RACKET_BRICKS;
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
                            App.ballSound.update();
                            App.ballSound.play();
                            handleBricksCollisionRules(targetBrick, ball, rules, i, j);
                        } else {
                            App.ballSound.update();
                            App.ballSound.play();
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
            ball.getDirection().setX(
                    -ball.getDirection().getX() + (ball.getRotation().getEffect() / 90) * ball.getDirection().getX());
        ball.getRotation().Collision();
        if (j != 0)
            ball.getDirection().setY(
                    -ball.getDirection().getY() + (ball.getRotation().getEffect() / 90) * ball.getDirection().getY());
        ball.getRotation().Collision();

    }

    public boolean updateBricksStatus() {
        boolean destroyed = false;
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[0].length; j++) {
                if (bricks[i][j] != null && bricks[i][j].isDestroyed()) {
                    bricks[i][j] = null;
                    game.setScore(game.getScore()+10); 
                    destroyed = true;
                }
            }
        }
        return destroyed;
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
