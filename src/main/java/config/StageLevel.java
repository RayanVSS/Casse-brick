package config;

import entity.ball.Ball;
import entity.racket.Racket;
import save.PlayerData;

public class StageLevel {

    private int difficulty; // à titre indicatif
    private int unlockLevel;
    private Game game;

    public StageLevel(int difficulty, int unlockLevel, GameRules rules,
            Ball ball, Racket racket) {

        this.difficulty = difficulty;
        this.unlockLevel = unlockLevel;
        this.game = new Game(ball, racket, rules);
    }

    public void loadGame(PlayerData playerData) {
        if (playerData.getExpLevel() >= unlockLevel) {

        } else {
            System.out.println("Niveau requis non atteint.");
        }
    }

}
