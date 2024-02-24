package config;

public class GameRules {

    //options de jeu à activer et à implémenter...
    private boolean limitedTime;
    private boolean limitedBounces;
    private boolean randomSwitchBricks;
    private boolean colorRestriction; //pas sûr
    private boolean transparent;
    private boolean invisible; //pas sûr
    private boolean unbreakable;

    private int remainingTime = 150; // 2 minutes 30
    private int remainingBounces = 5; // rebonds restants

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
}
