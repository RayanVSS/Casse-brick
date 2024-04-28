package config;

import gui.Menu.MenuControllers.GameCustomizerController;
import save.PlayerData;
import utils.GameConstants;

public class StageLevel {

    private int difficulty;
    private int unlockLevel;
    private boolean completed;
    private int maxScore;
    private transient Game game;
    private boolean customGame;
    private transient GameCustomizerController gameReinitializer;

    public StageLevel(int difficulty, int unlockLevel, boolean customGame) {

        this.difficulty = difficulty;
        this.unlockLevel = unlockLevel;
    }

    public StageLevel(Game game, boolean customGame) {
        this.game = game;
        this.customGame = customGame;
    }

    public StageLevel(Game game) {
        this.game = game;
    }

    public boolean canLoadGame() {
        if (PlayerData.expLevel >= unlockLevel) {
            System.out.println("Chargement du jeu...");
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
        game.getBall().reset();
        game.getRacket().reset();
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
        if (!customGame) {
            if (!completed) {
                completed = true;
                this.setDifficulty(difficulty + 1);
                PlayerData.expLevel++;
            }
            if (game.getScore() > maxScore) {
                maxScore = game.getScore();
            }
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

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isCustomGame() {
        return customGame;
    }

    public GameCustomizerController getGameReinitializer() {
        return gameReinitializer;
    }

    public void setGameReinitializer(GameCustomizerController gameReinitializer) {
        this.gameReinitializer = gameReinitializer;
    }

}
