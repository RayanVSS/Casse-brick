package config;

import utils.GameConstants;

public class StageController {

    StageLevel[] stages;

    public StageController(int nbStages) {
        this.stages = new StageLevel[nbStages];
    }

    public void createStages() {
        for (int i = 0; i < stages.length; i++) {
            stages[i] = new StageLevel(i, i + 1, GameConstants.PRECONFIG_GAME_RULES[i],
                    GameConstants.PRECONFIG_GAME_BALL[i], GameConstants.PRECONFIG_GAME_RACKET[i],
                    GameConstants.PRECONFIG_GAME_ARRANGEMENT[i]); // ajustement de la difficulté à faire
        }
    }
}
