package save;

import config.Game;
import config.GameRules;
import config.StageLevel;
import config.StagesProgress;
import utils.GameConstants;

public class PlayerData {

    public static String pseudo;
    public static int expLevel;
    public static int money;
    public static StagesProgress stagesProgress;

    public static void initPlayerData() {
        pseudo = "";
        expLevel = 1;
        money = 0;
        stagesProgress = new StagesProgress(GameConstants.STAGES_QTY);
        stagesProgress.createStages();
    }

    /**
     * Récompenser une game non-custom
     */
    public static void rewardStageWin(StageLevel level) {
        int addMoney = calculateStageWinMoney(level);
        PlayerData.money += addMoney;
    }

    /**
     * Récompenser une game custom
     */
    public static void rewardCustomWin(StageLevel level) {
        int addMoney = calculateStageWinMoney(level);
        PlayerData.money += addMoney / 2;
    }

    private static int calculateStageWinMoney(StageLevel level) {
        int winMoney = level.getGame().getScore(); // Base
        winMoney += applyBonus(level.getGame());
        winMoney *= applyMultipliers(level);
        return winMoney;
    }

    private static int applyBonus(Game game) {
        int bonus = 0;
        if (game.getRules().isLimitedTime()) {
            bonus += game.getRules().getRemainingTime() / 5;
        }
        if (game.getRules().isLimitedBounces()) {
            bonus += game.getRules().getRemainingBounces();
        }
        bonus += game.getLife() * 15;
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
