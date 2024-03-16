package entity.racket;


import javafx.scene.input.KeyCode;
import physics.entity.Racket;
import java.util.Set;


public class DegradeRacket extends Racket {

    public DegradeRacket() {
        super(200, 20, 8, false, true);
    }

    public void handleKeyPress(Set<KeyCode> keysPressed){

    }

    public void handleKeyRelease(KeyCode event){

    }
}
