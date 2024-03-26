package config;

import java.util.ArrayList;
import java.util.Random;

import entity.EntityColor;
import physics.entity.Ball;
import entity.brick.Brick;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class GameRules {

    private BricksArrangement arrangement;

    // Options de jeu : true pour "activé", règles qui s'apliquent lors de la partie
    private boolean limitedTime;
    private boolean limitedBounces;
    private boolean randomSwitchBricks;
    private boolean colorRestricted;
    private boolean transparent;
    private boolean unbreakable;

    // Variables servant au bon fonctionnement des règles activées
    private int remainingTime = GameConstants.GR_REMAINING_TIME;
    private int remainingBounces = GameConstants.GR_REMAINING_BOUNCES;
    private int[][] initialBricksZone;
    private int qty_transparent = GameConstants.GR_DEFAULT_QTY_TRANSPARENT;
    private int qty_unbreakable = GameConstants.GR_DEFAULT_QTY_UNBREAKABLE;

    public GameRules(BricksArrangement arrangement, boolean limitedTime, boolean limitedBounces,
            boolean randomSwitchBricks, boolean colorRestricted, boolean transparent, boolean unbreakable) {

        this.arrangement = arrangement;
        this.limitedTime = limitedTime;
        this.limitedBounces = limitedBounces;
        this.randomSwitchBricks = randomSwitchBricks;
        this.colorRestricted = colorRestricted;
        this.transparent = transparent;
        this.unbreakable = unbreakable;
    }

    /**
     * Classe définissant les différentes dispositions initiales des briques
     */
    public enum BricksArrangement {
        DEFAULT, RANDOM;
    }

    public boolean check() {
        return verifyLimitedTime() && verifyLimitedBounces();
    }

    public void initRules(Game game) {
        if (randomSwitchBricks) {
            registerInitialBricksZone(game.getMap());
        }
        if (colorRestricted) {
            applyColorOnBricks(game.getMap().getBricks());
        }
    }

    public void updateRemainingTime() {
        if (limitedTime) {
            remainingTime--;
        }
    }

    private boolean verifyLimitedTime() {
        if (limitedTime) {
            return remainingTime > 0;
        }
        return true;
    }

    public void updateRemainingBounces() {
        if (limitedBounces) {
            remainingBounces--;
        }
    }

    private boolean verifyLimitedBounces() {
        if (limitedBounces) {
            return remainingBounces >= 0;
        }
        return true;
    }

    private void registerInitialBricksZone(Map map) {
        // Zone de brique toujours supposée rectangulaire
        int[] coinSupGauche = null, coinInfDroit = { 0, 0 };
        for (int i = 0; i < map.getBricks().length; i++) {
            for (int j = 0; j < map.getBricks()[i].length; j++) {
                if (map.getBricks()[i][j] != null) {
                    if (coinSupGauche == null) {
                        coinSupGauche = new int[] { i, j };
                    }
                    if (i > coinInfDroit[0] || j > coinInfDroit[1]) {
                        coinInfDroit = new int[] { i, j };
                    }
                }
            }
        }
        initialBricksZone = new int[][] { coinSupGauche, coinInfDroit };
        // System.out.println("Zone de jeu : ");
        // System.out.println("Debut : " + initialBricksZone[0][0] + " , " + initialBricksZone[0][1]);
        // System.out.println("Fin : " + initialBricksZone[1][0] + " , " + initialBricksZone[1][1]);
    }

    public void shuffleBricks(Brick[][] bricks) {
        if (randomSwitchBricks) {
            Random rnd = new Random();
            for (int i = initialBricksZone[0][0]; i < initialBricksZone[1][0] + 1; i++) {
                for (int j = initialBricksZone[0][1]; j < initialBricksZone[1][1] + 1; j++) {

                    int m = rnd.nextInt((initialBricksZone[1][0] - initialBricksZone[0][0] + 1))
                            + initialBricksZone[0][0];

                    int n = rnd.nextInt((initialBricksZone[1][1] - initialBricksZone[0][1] + 1))
                            + initialBricksZone[0][1];

                    if (bricks[i][j] == null && bricks[m][n] != null) {
                        bricks[i][j] = bricks[m][n];
                        bricks[i][j]
                                .setC(new Coordinates(i * GameConstants.BRICK_WIDTH, j * GameConstants.BRICK_HEIGHT));
                        bricks[m][n] = null;

                    } else if (bricks[i][j] != null && bricks[m][n] == null) {
                        bricks[m][n] = bricks[i][j];
                        bricks[m][n]
                                .setC(new Coordinates(m * GameConstants.BRICK_WIDTH, n * GameConstants.BRICK_HEIGHT));
                        bricks[i][j] = null;

                    } else if (bricks[i][j] != null && bricks[m][n] != null) {
                        Brick temp = bricks[i][j];
                        bricks[i][j] = bricks[m][n];
                        bricks[m][n] = temp;
                        Coordinates tempC = bricks[i][j].getC();
                        bricks[i][j].setC(bricks[m][n].getC());
                        bricks[m][n].setC(tempC);
                    }
                    // System.out.println("BRICK X:" + i + " Y:" + j + " VERS " + "BRICK X:" + m + " Y:" + n);
                }
            }
        }
    }

    public void applyColorOnBricks(Brick[][] bricks) {
        Random random = new Random();
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (bricks[i][j] != null) {
                    EntityColor rndColor = EntityColor.values()[random.nextInt(EntityColor.values().length)];
                    bricks[i][j].setColor(rndColor);
                }
            }
        }
    }

    public boolean haveBricksCollisionRules() {
        return colorRestricted || transparent || unbreakable;
    }

    public boolean verifyColor(Brick brick, Ball ball) {
        System.out.println("" + brick.getColor() + ball.getColor());
        return brick.getColor() == ball.getColor();
    }

    public void updateBricksTransparency(Map map) {

        if (transparent) {
            int apply = qty_transparent;
            ArrayList<Brick> tempList = map.getListOfBricks();
            Random random = new Random();
            while (apply > 0 && map.countBricks() > qty_transparent) {
                Brick rndBrick = tempList.get(random.nextInt(tempList.size()));
                if (!rndBrick.isTransparent()) {
                    apply--;
                    tempList.remove(rndBrick);
                    rndBrick.setTransparent(true);
                }
            }

            // On enleve dans les briques restantes ceux ayant l'effet transparent du dernier tour
            for (Brick brick : tempList) {
                if (brick.isTransparent()) {
                    brick.setTransparent(false);
                }
            }
        }
    }

    public void updateBricksUnbreakability(Map map) {
        if (unbreakable) {
            int apply = qty_unbreakable;
            ArrayList<Brick> tempList = map.getListOfBricks();
            Random random = new Random();
            while (apply > 0 && map.countBricks() > qty_unbreakable) {
                Brick rndBrick = tempList.get(random.nextInt(tempList.size()));
                if (!rndBrick.isUnbreakable()) {
                    apply--;
                    tempList.remove(rndBrick);
                    rndBrick.setUnbreakable(true);
                }
            }

            // On enleve dans les briques restantes ceux ayant l'effet unbreakable du dernier tour
            for (Brick brick : tempList) {
                if (brick.isUnbreakable()) {
                    brick.setUnbreakable(false);
                }
            }
        }
    }

    // GETTERS/SETTERS

    public BricksArrangement getArrangement() {
        return arrangement;
    }

    public boolean isLimitedTime() {
        return limitedTime;
    }

    public boolean isLimitedBounces() {
        return limitedBounces;
    }

    public boolean isRandomSwitchBricks() {
        return randomSwitchBricks;
    }

    public boolean isColorRestricted() {
        return colorRestricted;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getRemainingBounces() {
        return remainingBounces;
    }

    public void setRemainingBounces(int remainingBounces) {
        this.remainingBounces = remainingBounces;
    }

}
