package entity.racket;


import javafx.scene.input.KeyCode;
import java.util.Set;
import utils.GameConstants;
import java.util.Timer;
import java.util.TimerTask;

public class MagnetRacket extends Racket{
    //etat de la raquette 
    private static String état = "positif";
    private boolean change = false;
    

 // creation de la raquette
 public MagnetRacket() {
    super(200, 20, 8, false, false);
}

// Mouvement a l'appui des touches
public void handleKeyPress(Set<KeyCode> keysPressed) {
    for (KeyCode key : keysPressed) {
        if(key==GameConstants.LEFT){
            if (this.mX() > -largeur / 2)
                this.mX(this.mX() - speed);
        }
        if(key==GameConstants.RIGHT){
            if (this.mX() < GameConstants.DEFAULT_WINDOW_WIDTH - longueur - 70)
                this.mX(this.mX() + speed);
        }
        if(key==GameConstants.SPACE){
            setChange();
        }
    }
}

// Mouvement au relachement des touches
public void handleKeyRelease(KeyCode event) {   
}

public void setChange(){
    if(!change){
        changeEtat();
        System.out.println(état);
        starChange();
        change = true;
    }
}

public void starChange(){
    Timer BoostTimer = new Timer();
    BoostTimer.schedule(new TimerTask() {
        @Override
        public void run() {
            change = false;
            BoostTimer.cancel();// Arrêter le timer
        }
    }, 100); 
}



public void changeEtat() {
    if (état == "negatif") {
        état = "positif";
    } else {
        état = "negatif";
    }
}



public static String getEtat() {
    return état;
}
}
