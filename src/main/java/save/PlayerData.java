package save;

import config.Game;
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
    public static void rewardStageWin(Game game) {
        int addMoney = calculateStageWinMoney(game);
        PlayerData.money += addMoney;
    }

    /**
     * Récompenser une game custom
     */
    public static void rewardCustomWin(Game game) {
        int addMoney = calculateStageWinMoney(game);
        PlayerData.money += addMoney / 2;
    }

    private static int calculateStageWinMoney(Game game) {
        int winMoney = game.getScore(); // Base
        winMoney += applyBonus(game);
        winMoney *= applyMultipliers(game);
        return winMoney;
    }

    private static int applyBonus(Game game) {
        int bonus = 0;
        return bonus;
    }

    private static int applyMultipliers(Game game) {
        int multiplier = 1;
        return multiplier;
    }
}
