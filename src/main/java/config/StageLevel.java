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

    public boolean canLoadGame() {
        if (PlayerData.expLevel >= unlockLevel) {
            if (game == null) {
                this.game = new Game(
                        GameConstants.PRECONFIG_GAME_BALL[difficulty],
                        GameConstants.PRECONFIG_GAME_RACKET[difficulty],
                        GameConstants.PRECONFIG_GAME_RULES[difficulty]);
            }
            return true;
        } else {
            System.out.println("Niveau requis non atteint.");
            return false;
        }
    }

    public void resetGame() {
        game = new Game(
                GameConstants.PRECONFIG_GAME_BALL[difficulty],
                GameConstants.PRECONFIG_GAME_RACKET[difficulty],
                GameConstants.PRECONFIG_GAME_RULES[difficulty]);
    }

    public void lostAction() {

        // + autres actions futures
        resetGame();
    }

    public void winAction() {

        if (!completed) {
            completed = true;
            PlayerData.expLevel++;
        }
        if (game.getScore() > maxScore) {
            maxScore = game.getScore();
        }
        resetGame();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getUnlockLevel() {
        return unlockLevel;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public Game getGame() {
        return game;
    }

}
