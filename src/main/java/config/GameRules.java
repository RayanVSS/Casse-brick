package config;

public class GameRules {

    //options de jeu à activer et à implémenter...
    private boolean limitedTime;
    private boolean limitedBounces; //pas sûr
    private boolean randomSwitchBricks;
    private boolean colorRestriction; //pas sûr
    private boolean transparent;
    private boolean invisible;
    private boolean unbreakable;

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
        return true; //implements...
    }
}
