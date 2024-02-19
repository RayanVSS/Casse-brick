package save;

import config.StageController;
import utils.GameConstants;

public class PlayerData {

    private String name;
    private int expLevel;
    private int money; //Future monnaie ?
    private StageController stageController;

    public PlayerData() {
        name = "";
        expLevel = 1;
        money = 0;
        stageController = new StageController(GameConstants.STAGES_QTY);
    }

    public void generateStages() {

    }

    public String getName() {
        return name;
    }

    public int getExpLevel() {
        return expLevel;
    }

    public int getMoney() {
        return money;
    }

    public StageController getStageController() {
        return stageController;
    }

}
