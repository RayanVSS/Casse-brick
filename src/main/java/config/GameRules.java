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
import physics.geometry.Vector;
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
            for (int i = 0; i < bricks.size(); i++) {
                int j = rnd.nextInt(bricks.size());

                // Échanger les briques
                Brick brick1 = bricks.get(i);
                Brick brick2 = bricks.get(j);

                Coordinates tempC = brick1.getC();
                brick1.setC(brick2.getC());
                brick2.setC(tempC);
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
            // On enleve dans les briques restantes ceux ayant l'effet transparent du
            // dernier tour
            for (Brick brick : tempList) {
                if (brick.isTransparent()) {
                    brick.setTransparent(false);
                }
            }
            Random random = new Random();
            while (apply > 0 && map.countBricks() > qty_transparent) {
                Brick rndBrick = tempList.get(random.nextInt(tempList.size()));
                if (!rndBrick.isTransparent()) {
                    apply--;
                    rndBrick.setTransparent(true);
                }
            }
        }
    }

    public void updateBricksUnbreakability(Map map) {
        if (unbreakable) {
            int apply = qty_unbreakable;
            ArrayList<Brick> tempList = map.getBricks();
            // On enleve dans les briques restantes ceux ayant l'effet unbreakable du
            // dernier tour
            for (Brick brick : tempList) {
                if (brick.isUnbreakable()) {
                    brick.setUnbreakable(false);
                }
            }
            Random random = new Random();
            while (apply > 0 && map.countBricks() > qty_unbreakable) {
                Brick rndBrick = tempList.get(random.nextInt(tempList.size()));
                if (!rndBrick.isUnbreakable()) {
                    apply--;
                    rndBrick.setUnbreakable(true);
                }
            }
        }
    }

    public boolean canCollide(Brick brick) {
        if (transparent) {
            return !brick.isTransparent();
        }
        return true;
    }

    public boolean verifyInfinite(Map m, Coordinates raquetCoordinates) {
        ArrayList<Brick> brick = m.getBricks();
        Coordinates c;
        for (int i = brick.size() - 1; i > -1; i--) {
            c = m.getBricks().get(i).getC();
            if (c.getY() >= raquetCoordinates.getY()-GameConstants.INFINITE_DISTANCE_RACKET) {
                return false;
            }
        }
        return true;
    }

    public void infiniteUpdate(Map m, double vitesse) {
        ArrayList<Brick> list = m.getBricks();
        Vector deplace = new Vector(new Coordinates(0, vitesse));
        for (Brick b : list) {
            b.getC().setY(b.getC().getY() + vitesse);
            b.deplace(deplace);
        }
    }

    public void createBrickInfinite(Map m) {
        if (lastCreatedBrick == null || lastCreatedBrick.getC().getY() > 0) {
            ArrayList<Brick> newBricks = new ArrayList<>();
            for (int i = 0; i < 0 + GameConstants.MAP_WIDTH; i++) {
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
        lastCreatedBrick = null;
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