package physics.entity;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import gui.GraphicsFactory.RacketGraphics;

/***************************************************************************
 * Explication de classe pour la raquette *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*
 * 
 * @Base:
 * @var Coordonnee c : Coordonnée de la raquette
 * @var Vector direction : Direction de la raquette
 * @var int speed : Vitesse de la raquette
 * @var int longueur : Longueur de la raquette
 * @var int largeur : Largeur de la raquette
 * @var String shape : Forme de la raquette
 * @var boolean fixeY : Si la raquette est fixe en y
 * @var boolean jump : Si la raquette peut sauter
 * 
 * @Boost:
 * @var Boolean vitesseP :raquette a un boost de vitesse
 * @var Boolean vitesseM :raquette a un malus de vitesse
 * @var Boolean largeurP :raquette a un boost de largeur
 * @var Boolean largeurM :raquette a un malus de largeur
 * @var boolean freeze :le temps est freeze
 * 
 * @GET_et_SET:
 *              je pense pas qui'il y ait besoin d'expliquer ;)
 * 
 * @Collision:
 * @param b : Coordonnée de la balle
 * @return : true si il y a collision, false sinon
 * 
 * @Mouvement a l'appui des touches: :
 * @param keysPressed : toutes les touches appuyées
 * @param event       : la touche relachée
 * 
 * @Saut:
 *        pas fini pour l'instant
 * 
 * @forme:
 *         pour modifier la forme de la raquette il suffit de cahnger la
 *         variable shape
 *         valeurs : "rectangle", "losange", "rond","triangle"
 * @see RacketGraphics (pour voir comment est gere la forme de la raquette)
 * 
 *
 * @author Rayan Belhassen
 **************************************************************************/

public abstract class Racket {

    // base
    Coordinates c = new Coordinates(GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2.5,
            GameConstants.DEFAULT_WINDOW_HEIGHT - 50);
    public Vector direction = new Vector(c);
    public double speed;
    public int longueur;
    public int largeur;
    public String shape;
    public boolean fixeY;
    public boolean jump;
    

    // boost
    Boolean vitesseP = false;
    Boolean vitesseM = false;
    Boolean largeurP = false;
    Boolean largeurM = false;
    boolean freeze = false;
    boolean zhonya = false;
    boolean intensityBall = false;
    private long startTimer;
    // varible pour les boosts
    public static boolean StopBall = false;
    public static boolean AddIntensityBall = false;

    private long jumpStartTime;

    // creation de la raquette
    public Racket(int largeur, int longueur, String shape, int speed, boolean fixeY, boolean jump) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.shape = shape;
        this.speed = speed;
        this.fixeY = fixeY;
        this.jump = jump;
    }

    // Collision
    public boolean CollisionRacket(Coordinates c,String shape) {
        if (shape.equals("rectangle")) {
            return CollisionRectangle(c);
        } else if (shape.equals("triangle")) {
            return CollisionTriangle(c);
        } else if (shape.equals("rond")) {
            return CollisionRond(c);
        } else if (shape.equals("losange")) {
            return CollisionLosange(c);
        }
        return false;
    }

    public boolean CollisionRacket(Ball b) {
        if (shape.equals("rectangle")) {
            return CollisionRectangle(b);
        } else if (shape.equals("triangle")) {
            return CollisionTriangle(b);
        } else if (shape.equals("rond")) {
            return CollisionRond(b);
        } else if (shape.equals("losange")) {
            return CollisionLosange(b);
        }
        return false;
    }

    public boolean CollisionRectangle(Ball b) {
        // if (b.getC().getX() > this.c.getX() && b.getC().getX() < this.c.getX() +
        // this.largeur
        // && b.getC().getY() > this.c.getY()
        // && b.getC().getY() < this.c.getY() + this.longueur) {
        // b.getC().setY(this.getC().getY() - b.getRadius());
        // return true;
        // }
        // return false;
        double dx = Math.max(this.c.getX(), Math.min(b.getC().getX(), this.c.getX() + this.largeur));
        double dy = Math.max(this.c.getY(), Math.min(b.getC().getY(), this.c.getY() + this.longueur));
        double distance = Math.sqrt(
                (b.getC().getX() - dx) * (b.getC().getX() - dx) + (b.getC().getY() - dy) * (b.getC().getY() - dy));
        if (distance < b.getRadius()) {
            b.getC().setY(this.getC().getY() - b.getRadius());
            return true;
        }
        return false;
    }

    public boolean CollisionRectangle(Coordinates c) {
        if (c.getX() > this.c.getX() && c.getX() < this.c.getX() + this.largeur && c.getY() > this.c.getY()
                && c.getY() < this.c.getY() + this.longueur) {
            return true;
        }
        return false;
    }

    public boolean CollisionTriangle(Ball b) {
        // coordonnees du triangle
        double x1 = this.getC().getX();
        double y1 = this.getC().getY() - this.getLongueur() / 2;
        double x2 = this.getC().getX() + this.getLargeur() / 2;
        double y2 = this.getC().getY() + this.getLongueur() / 2;
        double x3 = this.getC().getX() - this.getLargeur() / 2;
        double y3 = this.getC().getY() + this.getLongueur() / 2;

        // verifier avec le produit vectoriel
        double denominator = (y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3);
        double a = ((y2 - y3) * (b.getC().getX() - x3) + (x3 - x2) * (b.getC().getY() - y3)) / denominator;
        double b1 = ((y3 - y1) * (b.getC().getX() - x3) + (x1 - x3) * (b.getC().getY() - y3)) / denominator;
        double b2 = 1 - a - b1;

        // balle touche triangle
        if (a > GameConstants.DEGRADERACKET_TOLERANCE && b1 > GameConstants.DEGRADERACKET_TOLERANCE && b2 > GameConstants.DEGRADERACKET_TOLERANCE && b.getDirection().getY()>0) {
            System.out.println(b.getC().getX() + "  " + this.getC().getX() + " " + b.getDirection().getX());

            double changeDirectionN = 1-GameConstants.DEGRADERACKET_CHANGE_DIRECTION;
            double changeDirectionP = 1+GameConstants.DEGRADERACKET_CHANGE_DIRECTION;
            
            // balle touche le haut du triangle
            if (b.getC().getX() > this.getC().getX() - 10 && b.getC().getX() < this.getC().getX() + 10) {
                b.getC().setY(b.getC().getY() - b.getRadius());

            // balle touche le cote gauche du triangle
            } else if (b.getC().getX() < this.getC().getX()) {
                // balle qui va vers la droite
                if (b.getDirection().getX() > 0) {
                    b.setDirection(new Vector(0.1+b.getDirection().getX() * changeDirectionN,-0.1+b.getDirection().getY() * changeDirectionP));
                // balle qui va vers la gauche
                } else {
                    b.setDirection(new Vector(-0.1+b.getDirection().getX() * changeDirectionP,0.1+b.getDirection().getY() * changeDirectionN));
                }

            // balle touche le cote droit du triangle
            } else {
                // balle qui va vers la droite
                if (b.getDirection().getX() > 0) {
                    b.setDirection(new Vector(-0.1+b.getDirection().getX() * changeDirectionP, 0.1+b.getDirection().getY() * changeDirectionN));
                // balle qui va vers la gauche
                } else {
                    b.setDirection(new Vector(0.1+b.getDirection().getX() * changeDirectionN, -0.1+ b.getDirection().getY() * changeDirectionP));
                }
            }
            b.setSpeed(b.getSpeed()*1.1);;
            return true;
        }
        return false;
    }

    public boolean CollisionTriangle(Coordinates c) {
        // coordonnees du triangle
        double x1 = this.getC().getX();
        double y1 = this.getC().getY() - this.getLongueur() / 2;
        double x2 = this.getC().getX() + this.getLargeur() / 2;
        double y2 = this.getC().getY() + this.getLongueur() / 2;
        double x3 = this.getC().getX() - this.getLargeur() / 2;
        double y3 = this.getC().getY() + this.getLongueur() / 2;

        // verifier avec le produit vectoriel
        double denominator = (y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3);
        double a = ((y2 - y3) * (c.getX() - x3) + (x3 - x2) * (c.getY() - y3)) / denominator;
        double b1 = ((y3 - y1) * (c.getX() - x3) + (x1 - x3) * (c.getY() - y3)) / denominator;
        double b2 = 1 - a - b1;

        // balle touche triangle
        if (a > 0 && b1 > 0 && b2 > 0) {
            return true;
        }
        return false;
    }


    public boolean CollisionRond(Ball b) {
        double rx = this.getC().getX(); // centre x
        double ry = this.getC().getY(); // centre y
        double radiusX = this.getLargeur() / 2; // Rayon horizontal de l'ellipse
        double radiusY = this.getLongueur() / 2; // Rayon vertical de l'ellipse
        ry += 170;
        double dx = b.getC().getX() - rx;
        double dy = b.getC().getY() - ry;
        // normalisation
        double nx = dx / radiusX;
        double ny = dy / radiusY;
        double distance = Math.sqrt(nx * nx + ny * ny);
        if (distance < b.getRadius()-0.2) {
            // Calculer l'angle d'incidence de la balle par rapport à la raquette
            double incidentAngle = Math.atan2(dy, dx);
    
            // Calculer le nouvel angle de rebond en fonction de l'angle d'incidence
            double newAngle = Math.PI + (Math.PI - incidentAngle);
    
            // Mettre à jour la direction de la balle pour refléter le nouvel angle de rebond
            double newDirectionX = Math.cos(newAngle)*1.1;
            double newDirectionY = Math.sin(newAngle)*1.1;
            b.setDirection(new Vector(newDirectionX, newDirectionY));
    
            return true;
        }
        return false;
    }
    
    
    public boolean CollisionRond(Coordinates c) {
        double rx = this.getC().getX(); // centre x
        double ry = this.getC().getY(); // centre y
        double radiusX = this.getLargeur() / 2; // Rayon horizontal de l'ellipse
        double radiusY = this.getLongueur() / 2; // Rayon vertical de l'ellipse
        ry += 170;
        double dx = c.getX() - rx;
        double dy = c.getY() - ry;
        // normalisation
        double nx = dx / radiusX;
        double ny = dy / radiusY;
        double distance = Math.sqrt(nx * nx + ny * ny);
        if (distance < 6.5) {
            return true;
        }
        return false;
    }

    public boolean CollisionLosange(Ball b) {
        double[] points = {
                this.getC().getX(), this.getC().getY() - this.getLongueur() / 2,
                this.getC().getX() + this.getLargeur() / 2, this.getC().getY(),
                this.getC().getX(), this.getC().getY() + this.getLongueur() / 2,
                this.getC().getX() - this.getLargeur() / 2, this.getC().getY()
        };

        double cx = b.getC().getX() - 0.40;
        double cy = b.getC().getY() - 0.40;

        int j = points.length - 2;
        for (int i = 0; i < points.length; i += 2) {
            double px1 = points[i];
            double py1 = points[i+1];
            double px2 = points[j];
            double py2 = points[j+1];

            if (((py1 <= cy && cy < py2) || (py2 <= cy && cy < py1)) && (cx < (px2 - px1) * (cy - py1) / (py2 - py1) + px1)) {
                return true;
            }
            j = i;
        }
        return false;
    }

    public boolean CollisionLosange(Coordinates c) {
        double[] points = {
                this.getC().getX(), this.getC().getY() - this.getLongueur() / 2,
                this.getC().getX() + this.getLargeur() / 2, this.getC().getY(),
                this.getC().getX(), this.getC().getY() + this.getLongueur() / 2,
                this.getC().getX() - this.getLargeur() / 2, this.getC().getY()
        };

        double cx = c.getX() - 0.40;
        double cy = c.getY() - 0.40;

        int j = points.length - 2;
        for (int i = 0; i < points.length; i += 2) {
            double px1 = points[i];
            double py1 = points[i + 1];
            double px2 = points[j];
            double py2 = points[j + 1];

            if (((py1 <= cy && cy < py2) || (py2 <= cy && cy < py1)) && (cx < (px2 - px1) * (cy - py1) / (py2 - py1) + px1)) {


                return true;
            }
            j = i;
        }
        return false;
    }

    // fonction obligatoire
    public abstract void handleKeyPress(Set<KeyCode> keysPressed);

    public abstract void handleKeyRelease(KeyCode event);

    // boost
    // boost VitesseP
    public void startVitesseP(int duration) {
        Timer BoostTimer = new Timer();
        startTimer = System.currentTimeMillis();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                vitesseP = false;// À la fin du délai réinitialiser le boost
                speed = speed / GameConstants.BOOST_VITESSEP;// Réinitialiser la vitesse
                BoostTimer.cancel();// Arrêter le timer
            }
        }, duration * 1000);
    }

    public void setVitesseP(Boolean vitesse) {
        if (!vitesseP) {
            this.vitesseP = vitesse;
            // Si le boost est activé, démarrer le timer
            if (vitesseP) {
                speed = this.speed * GameConstants.BOOST_VITESSEP;// Augmenter la vitesse
                startVitesseP(GameConstants.BOOST_DURATION_VITESSEP);
            }
        }
    }

    // boost VitesseM
    public void startVitesseM(int duration) {
        Timer BoostTimer = new Timer();
        startTimer = System.currentTimeMillis();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                vitesseM = false;
                speed = speed * GameConstants.BOOST_VITESSEM;
                BoostTimer.cancel();
            }
        }, duration * 1000);
    }

    public void setVitesseM(Boolean vitesse) {
        if (!vitesseM) {
            this.vitesseM = vitesse;
            if (vitesseM) {
                speed = this.speed / GameConstants.BOOST_VITESSEM;
                startVitesseM(GameConstants.BOOST_DURATION_VITESSEM);
            }
        }
    }

    // boost largeurP
    public void startlargeurP(int duration) {
        Timer BoostTimer = new Timer();
        startTimer = System.currentTimeMillis();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                largeurP = false;
                largeur = largeur - GameConstants.BOOST_LARGEURP;
                BoostTimer.cancel();
            }
        }, duration * 1000);
    }

    public void setlargeurP(Boolean Largeur) {
        if (!largeurP) {
            this.largeurP = Largeur;
            if (largeurP) {
                largeur = this.largeur + GameConstants.BOOST_LARGEURP;
                startlargeurP(GameConstants.BOOST_DURATION_LARGEURP);
            }
        }
    }

    // boost longueurM
    public void startLargeurM(int duration) {
        startTimer = System.currentTimeMillis();
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                largeur = largeur + GameConstants.BOOST_LARGEURM;
                largeurM = false;
                BoostTimer.cancel();
            }
        }, duration * 1000);
    }

    public void setLargeurM(Boolean Largeur) {
        if (!largeurM) {
            this.largeurM = Largeur;
            if (largeurM) {
                largeur = this.largeur - GameConstants.BOOST_LARGEURM;
                startLargeurM(GameConstants.BOOST_DURATION_LARGEURM);
            }
        }
    }

    // boost Freeze
    public void startFreeze(int duration, double tmp) {
        startTimer = System.currentTimeMillis();
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                freeze = false;
                speed = tmp;
                BoostTimer.cancel();
            }
        }, duration * 1000);
    }

    public void setFreeze(Boolean freeze) {
        if (!this.freeze) {
            this.freeze = freeze;
            if (this.freeze) {
                double tmp = this.speed;
                speed = 0;
                startFreeze(GameConstants.BOOST_DURATION_FREEZE, tmp);
            }
        }
    }

    // boost Zhonya
    public void startZhonya(int duration) {
        startTimer = System.currentTimeMillis();
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                zhonya = false;
                StopBall = false;
                BoostTimer.cancel();
            }
        }, duration * 1000);
    }

    public void setZhonya(Boolean zhonya) {
        if (!this.zhonya) {
            this.zhonya = zhonya;
            if (this.zhonya) {
                StopBall = true;
                startZhonya(GameConstants.BOOST_DURATION_ZHONYA);
            }
        }
    }

    // boost IntensityBall
    public void startIntensityBall(int duration) {
        startTimer = System.currentTimeMillis();
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                intensityBall = false;
                AddIntensityBall = false;
                BoostTimer.cancel();
            }
        }, duration * 1000);
    }

    public void setIntensityBall(Boolean intensityBall) {
        if (!this.intensityBall) {
            this.intensityBall = intensityBall;
            if (this.intensityBall) {
                AddIntensityBall = true;
                startIntensityBall(GameConstants.BOOST_DURATION_INTENSITY_BALL);
            }
        }
    }

    public void reset() {
        Coordinates c = new Coordinates(GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2.5,
                GameConstants.DEFAULT_WINDOW_HEIGHT - 50);
        Vector direction = new Vector(c);
        this.setC(c);
        this.setDirection(direction);
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

    public boolean getZhonya() {
        return zhonya;
    }

    public boolean getIntensityBall() {
        return intensityBall;
    }

    public String getShapeType() {
        return shape;
    }


    public long getBoostDuration() {
        return System.currentTimeMillis() - startTimer;
    }
}
