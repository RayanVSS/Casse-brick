package config;

import gui.Console;
import entity.ball.ClassicBall;
import entity.racket.ClassicRacket;
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
        if (PlayerData.expLevel >= unlockLevel || PlayerData.isAdmin) {
            if (PlayerData.expLevel < unlockLevel) { // ADMIN
                Console.systemDisplay("Niveau requis : " + unlockLevel);
                Console.systemDisplay("Niveau actuel : " + PlayerData.expLevel);
                Console.systemDisplay("Niveau requis non atteint... mais...");
                Console.systemDisplay("Droit Admin : Chargement du jeu autorisé.");

            }
            Console.systemDisplay("Chargement du jeu...");
            if (game == null) {
                this.game = new Game(
                        GameConstants.PRECONFIG_GAME_BALL[difficulty],
                        GameConstants.PRECONFIG_GAME_RACKET[difficulty],
                        GameConstants.PRECONFIG_GAME_RULES[difficulty]);
            }
            return true;
        } else {
            Console.systemDisplay("Niveau requis non atteint.");
            return false;
        }
    }

    public boolean canLoadGameForDisplay() {
        return PlayerData.expLevel >= unlockLevel || PlayerData.isAdmin;
    }

    public void resetGame() {
        game.resetBalls();
        game.getRacket().reset();
        game.getRules().reset();
        if (game.getRules().isInfinite()) {
            game = new Game(new ClassicBall(), new ClassicRacket(), GameConstants.INFINITE_MODE);
        } else {
            game = new Game(
                    GameConstants.PRECONFIG_GAME_BALL[difficulty],
                    GameConstants.PRECONFIG_GAME_RACKET[difficulty],
                    GameConstants.PRECONFIG_GAME_RULES[difficulty]);
        }
    }

    public void lostAction() {

        // + autres actions futures
        resetGame();
    }

    public void winAction() {

        if (!customGame) {
            PlayerData.rewardStageWin(this);
            if (!completed) {
                completed = true;
                PlayerData.expLevel++;
            }
            if (game.getScore() > maxScore) {
                maxScore = game.getScore();
            }
        } else {
            PlayerData.rewardCustomWin(this);
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
