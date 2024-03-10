package physics.entity;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/***************************************************************************
 *                  Explication de classe pour la raquette                 *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*
 *      Base: 
 * @var Coordonnee c : Coordonnée de la raquette 
 * @var Vector direction : Direction de la raquette 
 * @var int speed : Vitesse de la raquette 
 * @var int longueur : Longueur de la raquette 
 * @var int largeur : Largeur de la raquette 
 * @var boolean fixeY : Si la raquette est fixe en y 
 * @var boolean jump : Si la raquette peut sauter 
 *      
 *      boost: 
 * @var Boolean vitesseP :raquette a un boost de vitesse 
 * @var Boolean vitesseM :raquette a un malus de vitesse 
 * @var Boolean largeurP :raquette a un boost de largeur
 * @var Boolean largeurM :raquette a un malus de largeur
 * @var boolean freeze :le temps est freeze 
 *      
 *      GET et SET: 
 *je pense pas qui'il y ait besoin d'expliquer ;) 
 *        
 *      Collision: 
 * @param c : Coordonnée de l'objet avec lequel on veut vérifier la collision
 * @return : true si il y a collision, false sinon 
 *         
 *      Mouvement a l'appui des touches: :
 * @param keysPressed : toutes les touches appuyées 
 *
 *      Mouvement au relachement des touches: 
 * @param event: touche relachée 
 *                    
 *      Saut: 
 *pas fini pour l'instant 
 *
 * @author Rayan Belhassen 
 **************************************************************************/

public abstract class Racket {

    // base
    Coordinates c = new Coordinates(GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2.5, GameConstants.DEFAULT_WINDOW_HEIGHT - 50);
    Vector direction = new Vector(c);
    public double speed;
    public int longueur;
    public int largeur;
    boolean fixeY;
    public boolean jump;

    // boost
    Boolean vitesseP = false;
    Boolean vitesseM = false;
    Boolean largeurP = false;
    Boolean largeurM = false;
    boolean freeze = false;

    private long jumpStartTime;

    // creation de la raquette
    public Racket(int largeur, int longueur, int speed, boolean fixeY, boolean jump) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.speed = speed;
        this.fixeY = fixeY;
        this.jump = jump;
    }

    // Collision
    public boolean CollisionRacket(Coordinates c) {
        if (c.getX() > this.c.getX() && c.getX() < this.c.getX() + this.largeur && c.getY() > this.c.getY()
                && c.getY() < this.c.getY() + this.longueur) {
            return true;
        }
        return false;
    }

    public boolean CollisionRacket(Ball b) {
        // if (b.getC().getX() > this.c.getX() && b.getC().getX() < this.c.getX() + this.largeur
        //         && b.getC().getY() > this.c.getY()
        //         && b.getC().getY() < this.c.getY() + this.longueur) {
        //     b.getC().setY(this.getC().getY() - b.getRadius());
        //     return true;
        // }
        // return false;
        double dx = Math.max(this.c.getX(), Math.min(b.getC().getX(), this.c.getX() + this.largeur));
        double dy = Math.max(this.c.getY(), Math.min(b.getC().getY(), this.c.getY() + this.longueur));
        double distance = Math.sqrt((b.getC().getX() - dx) * (b.getC().getX() - dx) + (b.getC().getY() - dy) * (b.getC().getY() - dy));
        if (distance < b.getRadius()) {
            b.getC().setY(this.getC().getY() - b.getRadius());
            return true;
        }
        return false;
    }

    public boolean CollisionRacket2(Ball b) {
        boolean verifX = c.getX() > getC().getX() && c.getX() < getC().getX() + largeur;
        boolean verifY = c.getY() > getC().getY() && c.getY() < getC().getY() + longueur;
        boolean verifX1 = c.getX()<= getC().getX() && c.getX() > getC().getX() - b.getRadius() || c.getX() >= getC().getX() + largeur && c.getX() < getC().getX() + largeur + b.getRadius();
        boolean verifY1 =  c.getY() >= getC().getY() && c.getY() < getC().getY() + longueur;
        if(verifX1 && verifY1){
            b.getDirection().setX(-b.getDirection().getX());
            Ball.CollisionR_Side=true;
            return true;
        }
        return verifX && verifY;
    } 


    // fonction obligatoire
    public abstract void handleKeyPress(Set<KeyCode> keysPressed);

    public abstract void handleKeyRelease(KeyCode event);


    // boost
    //boost VitesseP
    public void startVitesseP(int duration) {
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                vitesseP = false;// À la fin du délai réinitialiser le boost 
                speed = speed / GameConstants.BOOST_VITESSEP;// Réinitialiser la vitesse
                BoostTimer.cancel();// Arrêter le timer
            }
        }, duration*1000); 
    }


    public void setVitesseP(Boolean vitesse) {
        if (!vitesseP){
            this.vitesseP = vitesse;
            // Si le boost est activé, démarrer le timer
            if (vitesseP) {
                speed = this.speed * GameConstants.BOOST_VITESSEP;// Augmenter la vitesse
                startVitesseP(GameConstants.BOOST_DURATION_VITESSEP);
            }
        }
    }

    //boost VitesseM
    public void startVitesseM(int duration) {
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                vitesseM = false;
                speed = speed * GameConstants.BOOST_VITESSEM;
                BoostTimer.cancel();
            }
        }, duration*1000); 
    }

    public void setVitesseM(Boolean vitesse) {
        if (!vitesseM){
            this.vitesseM = vitesse;
            if (vitesseM) {
                speed = this.speed / GameConstants.BOOST_VITESSEM;
                startVitesseM(GameConstants.BOOST_DURATION_VITESSEM);
            }
        }
    }

    //boost largeurP
    public void startlargeurP(int duration) {
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                largeurP = false;
                largeur = largeur - GameConstants.BOOST_LARGEURP;
                BoostTimer.cancel();
            }
        }, duration*1000); 
    }

    public void setlargeurP(Boolean Largeur) {
        if (!largeurP){
            this.largeurP = Largeur;
            if (largeurP) {
                largeur = this.largeur + GameConstants.BOOST_LARGEURP;
                startlargeurP(GameConstants.BOOST_DURATION_LARGEURP);
            }
        }
    }

    //boost longueurM
    public void startLargeurM(int duration) {
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                largeur = largeur + GameConstants.BOOST_LARGEURM;
                largeurM = false; 
                BoostTimer.cancel();
            }
        }, duration*1000); 
    }

    public void setLargeurM(Boolean Largeur) {
        if (!largeurM){
            this.largeurM = Largeur;
            if (largeurM) {
                largeur = this.largeur - GameConstants.BOOST_LARGEURM;
                startLargeurM(GameConstants.BOOST_DURATION_LARGEURM);
            }
        }
    }

    //boost Freeze
    public void startFreeze(int duration, double tmp) {
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                freeze = false;
                speed = tmp; 
                BoostTimer.cancel();
            }
        }, duration*1000); 
    }

    public void setFreeze(Boolean freeze) {
        if (!this.freeze){
            this.freeze = freeze;
            if (this.freeze) {
                double tmp = this.speed;
                speed = 0;
                startFreeze(GameConstants.BOOST_DURATION_FREEZE,tmp);
            }
        }
    }

    // GET et SET
    public Boolean getJump() {
        return jump;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public Coordinates getC() {
        return c;
    }

    public void setC(Coordinates c) {
        this.c = c;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
    
    public void mMouseMove(MouseEvent e) {
        this.mOnMouseMoved(e);
    }

    private void mOnMouseMoved(MouseEvent e) {
        this.mX((int) e.getX());
    }

    public void mX(double pX) {
        this.c.setX(pX);
    }

    public void mY(double pY) {
        this.c.setY(pY);
    }

    public double mX() {
        return this.c.getX();
    }

    public double mY() {
        return this.c.getY();
    }

    public long getJumpStartTime() {
        return jumpStartTime;
    }

    public void setJumpStartTime(long jumpStartTime) {
        this.jumpStartTime = jumpStartTime;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }


    public Boolean getVitesseP() {
        return vitesseP;
    }


    public Boolean getVitesseM() {
        return vitesseM;
    }


    public Boolean getLargeurP() {
        return largeurP;
    }

    public Boolean getLargeurM() {
        return largeurM;
    }

    public boolean getFreeze() {
        return freeze;
    }




}
