package config;

import entity.ball.Ball;
import entity.racket.Racket;
import save.PlayerData;
import utils.GameConstants;

public class StageLevel {

    private int difficulty;
    private int unlockLevel;
    private boolean completed;
    private int maxScore;
    private transient Game game;

    public StageLevel(int difficulty, int unlockLevel) {

        this.difficulty = difficulty;
        this.unlockLevel = unlockLevel;
    }

    public void loadGame() {
        if (PlayerData.expLevel >= unlockLevel) {
            if (game == null) {
                this.game = new Game(
                        GameConstants.PRECONFIG_GAME_BALL[difficulty],
                        GameConstants.PRECONFIG_GAME_RACKET[difficulty],
                        GameConstants.PRECONFIG_GAME_RULES[difficulty]);
            }
        } else {
            System.out.println("Niveau requis non atteint.");
        }
    }

    public void resetGame() {
        game = new Game(
                GameConstants.PRECONFIG_GAME_BALL[difficulty],
                GameConstants.PRECONFIG_GAME_RACKET[difficulty],
                GameConstants.PRECONFIG_GAME_RULES[difficulty]);
    }

}
