package save;

import config.StagesProgress;
import utils.GameConstants;

public class PlayerData {

    public static String pseudo;
    public static int expLevel;
    public static int money; //Future monnaie ?
    public static StagesProgress stagesProgress;

    public static void initPlayerData() {
        pseudo = "";
        expLevel = 1;
        money = 0;
        stagesProgress = new StagesProgress(GameConstants.STAGES_QTY);
        stagesProgress.createStages();
    }

}
