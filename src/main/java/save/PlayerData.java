package save;

import config.GameRules;
import config.StageLevel;
import config.StagesProgress;
import gui.Inventaire;
import utils.GameConstants;

public class PlayerData {

    public static String pseudo;
    public static int expLevel;
    public static int money;
    public static boolean isAdmin;
    public static StagesProgress stagesProgress;
    public static Inventaire inventaire;

    public static void initPlayerData() {
        pseudo = "Sans nom";
        expLevel = 1;
        money = 0;
        isAdmin = false;
        stagesProgress = new StagesProgress(GameConstants.STAGES_QTY);
        stagesProgress.createStages();
        inventaire = new Inventaire();
        
    }

    /**
     * Récompenser une game non-custom
     */
    public static void rewardStageWin(StageLevel level) {
        int addMoney = calculateStageWinMoney(level);
        PlayerData.money += addMoney;
        GameConstants.LAST_WIN_MONEY = addMoney;
    }

    /**
     * Récompenser une game custom
     */
    public static void rewardCustomWin(StageLevel level) {
        int addMoney = calculateStageWinMoney(level);
        PlayerData.money += addMoney / 2;
        GameConstants.LAST_WIN_MONEY = addMoney;
    }

    private static int calculateStageWinMoney(StageLevel level) {

        int winMoney = level.getGame().getScore(); // Base
        winMoney += applyBonus(level);
        winMoney *= applyMultipliers(level);
        System.out.println("Vous avez gagné : " + winMoney);
        return winMoney;
    }

    private static int applyBonus(StageLevel level) {

        int bonus = 0;
        GameRules rules = level.getGame().getRules();
        if (rules.isLimitedTime()) {
            bonus += rules.getRemainingTime() / 5;
        }
        if (rules.isLimitedBounces()) {
            bonus += rules.getRemainingBounces();
        }
        bonus += level.getGame().getLife() * 15;
        bonus += (level.getDifficulty() + 1) * 5;
        return bonus;
    }

    private static double applyMultipliers(StageLevel level) {

        double multiplier = 1.00;
        if (!level.isCompleted()) {
            multiplier *= 2.00;
        }
        GameRules rules = level.getGame().getRules();
        if (rules.isLimitedTime()) {
            multiplier *= 1.50;
        }
        if (rules.isLimitedBounces()) {
            multiplier *= 1.40;
        }
        if (rules.isRandomSwitchBricks()) {
            multiplier *= 1.30;
        }
        if (rules.isColorRestricted()) {
            multiplier *= 1.20;
        }
        if (rules.isTransparent()) {
            multiplier *= 1.05;
        }
        if (rules.isUnbreakable()) {
            multiplier *= 1.10;
        }
        return multiplier;
    }
}
