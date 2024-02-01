package config;

import entity.ball.Ball;
import entity.racket.Racket;

public class GameClassic extends Game {

    public GameClassic() {
        super(new Ball(), new Racket()); // sera BallClassic et RacketClassic plus tard
    }
}
