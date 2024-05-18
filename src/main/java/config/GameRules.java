package config;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.checkerframework.checker.units.qual.C;

import entity.EntityColor;
import entity.brick.BrickClassic;
import physics.entity.Ball;
import physics.entity.Brick;
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
    private boolean infinite;
    private boolean infiniteStop;

    // Variables servant au bon fonctionnement des règles activées
    private int remainingTime = GameConstants.GR_REMAINING_TIME;
    private int remainingBounces = GameConstants.GR_REMAINING_BOUNCES;
    private int qty_transparent = GameConstants.GR_DEFAULT_QTY_TRANSPARENT;
    private int qty_unbreakable = GameConstants.GR_DEFAULT_QTY_UNBREAKABLE;
    private Brick lastCreatedBrick = null;

    // Variables initiales servant au reset
    private int initalRemainingTime = remainingTime;
    private int initalRemainingBounces = remainingBounces;

    public GameRules(BricksArrangement arrangement, boolean limitedTime, boolean limitedBounces,
            boolean randomSwitchBricks, boolean colorRestricted, boolean transparent, boolean unbreakable,
            boolean infinite) {

        this.arrangement = arrangement;
        this.limitedTime = limitedTime;
        this.limitedBounces = limitedBounces;
        this.randomSwitchBricks = randomSwitchBricks;
        this.colorRestricted = colorRestricted;
        this.transparent = transparent;
        this.unbreakable = unbreakable;
        this.infinite = infinite;
        this.infiniteStop = false;
    }

    /**
     * Classe définissant les différentes dispositions initiales des briques
     */
    public enum BricksArrangement {
        DEFAULT, RANDOM, INFINITE;
    }

    public boolean check(Map m, Coordinates cr) {
        return verifyLimitedTime() && verifyLimitedBounces() && verifyInfinite(m, cr);
    }

    public void initRules(Game game) {
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

    // Vérification des règles qui s'appliquent au contact avec la raquette
    public void updateRulesRacket(Map map) {
        updateRemainingBounces();
        updateBricksTransparency(map);
        updateBricksUnbreakability(map);
        shuffleBricks(map.getBricks());
    }

    public void shuffleBricks(ArrayList<Brick> bricks) {
        if (randomSwitchBricks) {
            Random rnd = new Random();
            for (Brick brick1 : bricks) {
                Coordinates b1C = brick1.getC();
                Brick brick2 = bricks.get(rnd.nextInt(bricks.size()));
                brick1.setC(brick2.getC());
                brick2.setC(b1C);
            }
        }
    }

    public void applyColorOnBricks(ArrayList<Brick> bricks) {
        Random random = new Random();
        for (Brick brick : bricks) {
            EntityColor rndColor = EntityColor.values()[random.nextInt(EntityColor.values().length)];
            brick.setColor(rndColor);
        }
    }

    public boolean haveBricksCollisionRules() {
        return colorRestricted || transparent || unbreakable;
    }

    public boolean verifyColor(Brick brick, Ball ball) {
        return brick.getColor() == ball.getColor();
    }

    public void updateBricksTransparency(Map map) {

        if (transparent) {
            int apply = qty_transparent;
            ArrayList<Brick> tempList = map.getBricks();
            Random random = new Random();
            while (apply > 0 && map.countBricks() > qty_transparent) {
                Brick rndBrick = tempList.get(random.nextInt(tempList.size()));
                if (!rndBrick.isTransparent()) {
                    apply--;
                    tempList.remove(rndBrick);
                    rndBrick.setTransparent(true);
                }
            }

            // On enleve dans les briques restantes ceux ayant l'effet transparent du
            // dernier tour
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
            ArrayList<Brick> tempList = map.getBricks();
            Random random = new Random();
            while (apply > 0 && map.countBricks() > qty_unbreakable) {
                Brick rndBrick = tempList.get(random.nextInt(tempList.size()));
                if (!rndBrick.isUnbreakable()) {
                    apply--;
                    tempList.remove(rndBrick);
                    rndBrick.setUnbreakable(true);
                }
            }

            // On enleve dans les briques restantes ceux ayant l'effet unbreakable du
            // dernier tour
            for (Brick brick : tempList) {
                if (brick.isUnbreakable()) {
                    brick.setUnbreakable(false);
                }
            }
        }
    }

    public boolean verifyInfinite(Map m, Coordinates raquetCoordinates) {//TODO A tâtonner
        Coordinates c = m.getBricks().get(m.getBricks().size() - 1).getC();
        if (c.getY() >= raquetCoordinates.getY() - 31) {//TODO AJOUTER DS GAMECONSTANTE
            return false;
        }
        return true;
    }

    public void infiniteUpdate(Map m, double vitesse) {
        ArrayList<Brick> list = m.getBricks();
        for (Brick b : list) {
            b.getC().setY(b.getC().getY() + vitesse);
            // Coordinates c = new Coordinates(b.getC().getX(), b.getC().getY() + vitesse);
            // if (m.inMap((int) c.getIntX() / GameConstants.BRICK_WIDTH,
            //         (int) c.getIntY() / GameConstants.BRICK_HEIGHT)) {
            //     b.setC(c);
            // }
        }
    }

    public void setInfiniteStop(boolean infiniteStop, Map m, int duration) {
        if (!this.infiniteStop) {
            this.infiniteStop = infiniteStop;
            if (this.infiniteStop) {
                infiniteStop(m, duration);
            }
        }

    }

    public void testInfinite(Map m) {
        if (!this.infiniteStop) {
            infiniteUpdate(m, 0.60);
        }
    }

    public void infiniteStop(Map map, int duration) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                infiniteUpdate(map, 0);
                infiniteStop = false;
            }
        };
        timer.schedule(task, duration);

    }

    public void createBrickInfinite(Map m) {
        if (lastCreatedBrick == null || lastCreatedBrick.getC().getY() > 0) {
            ArrayList<Brick> newBricks = new ArrayList<>();
            int indexFirstColumn = (GameConstants.MAP_WIDTH - m.getColumnsBricks()) / 2;
            for (int i = indexFirstColumn; i < indexFirstColumn + m.getColumnsBricks(); i++) {
                Brick temp = new BrickClassic(new Coordinates(i * GameConstants.BRICK_WIDTH,
                        -1 * GameConstants.BRICK_HEIGHT));
                if (isColorRestricted()) {
                    temp.setColor(EntityColor.values()[new Random().nextInt(EntityColor.values().length)]);
                }
                newBricks.add(temp);
            }
            if (newBricks.size() > 0) {
                lastCreatedBrick = newBricks.get(0);
                m.getBricks().addAll(newBricks);
            }
        }
    }

    public ArrayList<Brick> createeBrickInfinite(Map m) {
        ArrayList<Brick> b = new ArrayList<>();
        for (int i = 0; i < m.getColumnsBricks(); i++) {
            for (int j = 0; j < m.getRowsBricks(); j++) {
                Brick brick = new BrickClassic(
                        new Coordinates(i * GameConstants.BRICK_WIDTH, j * GameConstants.BRICK_HEIGHT));
                if (isColorRestricted()) {
                    EntityColor rndColor = EntityColor.values()[new Random().nextInt(EntityColor.values().length)];
                    brick.setColor(rndColor);
                }
                b.add(brick);
            }
        }
        return b;
    }

    // Redéfinie les valeurs à appliquer lors du reset
    public void redefInitialResetTime(int resetRemainingTime) {
        if (limitedTime) {
            initalRemainingTime = resetRemainingTime;
            remainingTime = initalRemainingTime;
        }
    }

    public void redefInitialResetBounces(int resetRemainingBounces) {
        if (limitedBounces) {
            initalRemainingBounces = resetRemainingBounces;
            remainingBounces = initalRemainingBounces;
        }
    }

    public void reset() {
        remainingTime = initalRemainingTime;
        remainingBounces = initalRemainingBounces;
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

    public boolean isInfinite() {
        return this.infinite;
    }

}

// private boolean verifyInfinitee(ArrayList<Brick> bricks) {
//     for (int i = 0; i < bricks.length; i++) {
//         if (bricks[i][bricks[0].length - 2] != null) {
//             return false;
//         }
//     }
//     return true;
// }

// private boolean verifyInfinitee(ArrayList<Brick> bricks) {
//     for (int i = bricks.size()-1; i>=0; i--) {
//         if (bricks.get(i).getC().getY() >= GameConstants.DEFAULT_WINDOW_WIDTH) {
//             return false;
//         }
//     }
//     return true;
// }

// public void infiniteUpzdate(Map m, double vitesse) {
//     ArrayList<Brick> list = m.getBricks();
//     for (int i = list.size() - 1; i > -1; i--) {
//         Brick b = list.get(i);
//         Coordinates c = new Coordinates(b.getC().getX(), b.getC().getY() + vitesse);
//         if (m.inMap(c.getIntX() / GameConstants.BRICK_WIDTH, c.getIntY() / GameConstants.BRICK_HEIGHT)) {
//             bricks[(int) b.getC().getX() / GameConstants.BRICK_WIDTH][(int) b.getC().getY()
//                     / GameConstants.BRICK_HEIGHT] = null;
//             b.setC(c);
//             bricks[(int) b.getC().getX() / GameConstants.BRICK_WIDTH][(int) b.getC().getY()
//                     / GameConstants.BRICK_HEIGHT] = b;
//         }
//     }
// }

// public ArrayList<Brick> createBrickInfinite(Brick[][] bricks){
//     ArrayList<Brick> b=new ArrayList<>();
//     for (int i = 0; i < bricks.length; i++) {
//         for (int j = 0; j < bricks[0].length; j++) {
//             if (bricks[i][j] == null) {
//                 bricks[i][j] = new BrickClassic(
//                         new Coordinates(i * GameConstants.BRICK_WIDTH, j * GameConstants.BRICK_HEIGHT));
//                 if (isColorRestricted()){
//                     EntityColor rndColor = EntityColor.values()[new Random().nextInt(EntityColor.values().length)];
//                     bricks[i][j].setColor(rndColor);
//                 }
//                 b.add(bricks[i][j]);
//             } else {
//                 break;
//             }
//         }
//     }
//     return b;
// }