package config;

import java.util.Random;

import entity.brick.Brick;
import geometry.Coordinates;
import utils.GameConstants;

public class GameRules {

    //options de jeu à activer et à implémenter...
    private boolean limitedTime;
    private boolean limitedBounces;
    private boolean randomSwitchBricks;
    private boolean colorRestriction; // pas sûr
    private boolean transparent;
    private boolean invisible; // pas sûr
    private boolean unbreakable;

    private int remainingTime = 150; // 2 minutes 30
    private int remainingBounces = 50; // rebonds restants
    private int[][] initialBricksZone;

    public GameRules() {

    }

    public GameRules(boolean limitedTime, boolean limitedBounces, boolean randomSwitchBricks, boolean colorRestriction,
            boolean transparent, boolean invisible, boolean unbreakable) {

        this.limitedTime = limitedTime;
        this.limitedBounces = limitedBounces;
        this.randomSwitchBricks = randomSwitchBricks;
        this.colorRestriction = colorRestriction;
        this.transparent = transparent;
        this.invisible = invisible;
        this.unbreakable = unbreakable;
    }

    public boolean apply() {
        return verifyLimitedTime() && verifyLimitedBounces();
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

    public void registerInitialBricksZone(Map map) {
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
}
