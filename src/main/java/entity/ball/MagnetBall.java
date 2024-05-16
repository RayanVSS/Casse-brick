package entity.ball;

import entity.racket.MagnetRacket;
import physics.entity.Ball;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;
import utils.Key;

/**
 * Alors si tu lis cette phrase c'est que tu dois modifier ou comprendre le code
 * BONNE CHANCE!
 * 
 * @basse***********************************************
 * @var etatRacket: Negatif ou Positif
 * @var etatBall: Positif (par defaut mais peut etre negatif)
 * @var Front: true si la balle est devant la raquette
 * @var getRa: raquette dans le partie
 * @var mouvement: pour savoir si la touche est appuyée
 * @var basX hautX, basY, hautY: pour calculer la vitesse de la balle
 * 
 * @fonction*******************************************
 * @movement: methode qui permet de deplacer la balle
 * 
 * @side_attraction_more: methode qui permet d'attirer la balle vers le centre
 *                        de la raquette
 * 
 * @side_attraction_less: methode qui permet de repousser la balle sur les cotes
 *                        de la raquette
 * 
 * @Affiche_vitesse: methode qui permet d'afficher la vitesse de la balle
 * 
 * @author Rayan Belhassen
 */

public class MagnetBall extends Ball {
    private static String etatRacket = MagnetRacket.getEtat();// etat de la Raquette
    private String etatBall = "positif"; // etat de la balle
    private boolean Front = false;// si la balle est devant la raquette
    public static Racket getRa; // coordonnées de la raquette
    private Key mouvement = new Key(); // pour savoir si la touche est appuyée

    // variable pour calculer la vitesse de la balle
    private int basX = 9999;
    private int hautX = 0;
    private int basY = 9999;
    private int hautY = 0;

    public MagnetBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES.clone(), new Vector(new Coordinates(0, 1)).clone(),
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setWindow(GameConstants.DEFAULT_GAME_ROOT_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    public MagnetBall(int d) {
        super(d);
    }

    @Override
    public void movement(long deltaT) {
        double w = getZoneWidth();
        double h = getZoneHeight();
        double newX = this.getC().getX() + this.getDirection().getX() * (this.getSpeed()) / 4;
        double newY = this.getC().getY() + this.getDirection().getY() * (this.getSpeed()) / 4;
        this.getRotation().stopRotation();
        // limite de vitesse de la balle
        if (GameConstants.LIMITE_SPEED_MAGNET) {
            double speedY = newY - this.getC().getY();
            double speedX = newX - this.getC().getX();
            if (speedY > GameConstants.VITESSE_MAX_MAGNET) {
                newY = this.getC().getY() + GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedY < -GameConstants.VITESSE_MAX_MAGNET) {
                newY = this.getC().getY() - GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedY > -1.5 && speedY <= 0) {
                newY -= 0.2;
            }
            if (speedY < 1.5 && speedY >= 0) {
                newY += 0.2;
            }
            if (speedX > GameConstants.VITESSE_MAX_MAGNET) {
                newX = this.getC().getX() + GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedX < -GameConstants.VITESSE_MAX_MAGNET) {
                newX = this.getC().getX() - GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedX > -1.5 && speedX <= 0) {
                newX -= 0.2;
            }
            if (speedX < 1.5 && speedX >= 0) {
                newX += 0.2;
            }
        }
        if (Front) { // verifie si la balle est devant la raquette avant n'importe action
            etatRacket = MagnetRacket.getEtat();// actualise l'etat de la raquette
            if (etatRacket == etatBall) {// si l'etat de la raquette est la meme que la balle
                if (!CollisionR) {
                    // attraction de la balle vers la raquette qui est moin puissante si la balle
                    // est loin de la raquette
                    if (getRa.getC().getY() - this.getC().getY() < 10) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET);
                        side_attraction_more(1.0);
                    } else if (getRa.getC().getY() - this.getC().getY() < 20) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 1.2);
                        side_attraction_more(1.2);
                    } else if (getRa.getC().getY() - this.getC().getY() < 40) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 1.4);
                        side_attraction_more(1.4);
                    } else if (getRa.getC().getY() - this.getC().getY() < 60) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 1.6);
                        side_attraction_more(1.6);
                    } else if (getRa.getC().getY() - this.getC().getY() < 80) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 1.8);
                        side_attraction_more(1.8);
                    } else if (getRa.getC().getY() - this.getC().getY() < 100) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 2.0);
                        side_attraction_more(2.0);
                    } else if (getRa.getC().getY() - this.getC().getY() < 120) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 2.2);
                        side_attraction_more(2.2);
                    } else if (getRa.getC().getY() - this.getC().getY() < 140) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 2.4);
                        side_attraction_more(2.4);
                    } else if (getRa.getC().getY() - this.getC().getY() < 160) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 2.6);
                        side_attraction_more(2.6);
                    } else if (getRa.getC().getY() - this.getC().getY() < 200) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 2.8);
                        side_attraction_more(2.8);
                    } else if (getRa.getC().getY() - this.getC().getY() < 240) {
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET / 3.0);
                        side_attraction_more(3.0);
                    }
                } else {
                    // si la balle est en collision avec la raquette alors la balle reste coller a
                    // la raquette
                    this.setC(new Coordinates(newX, newY)); // la balle reste coller a la raquette
                    this.getDirection().setX(0); 
                    this.getDirection().setY(1);
                    CollisionR = true; //la balle est en collision avec la raquette
                    if (mouvement.contains(GameConstants.LEFT)) {
                        this.setC(new Coordinates(newX - getRa.getSpeed(), newY));
                    }
                    if (mouvement.contains(GameConstants.RIGHT)) {
                        this.setC(new Coordinates(newX + getRa.getSpeed(), newY));
                    }
                    return;
                }
            }
            // si l'etat de la raquette est different de la balle
            if (etatRacket != etatBall) {
                CollisionR = false;
                // repulsion de la balle de la raquette qui est moin puissante si la balle est
                // loin de la raquette
                if (getRa.getC().getY() - this.getC().getY() < 10) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET);
                    side_attraction_less(1.0);
                } else if (getRa.getC().getY() - this.getC().getY() < 20) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 1.2);
                    side_attraction_less(1.2);
                } else if (getRa.getC().getY() - this.getC().getY() < 40) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 1.4);
                    side_attraction_less(1.4);
                } else if (getRa.getC().getY() - this.getC().getY() < 60) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 1.6);
                    side_attraction_less(1.6);
                } else if (getRa.getC().getY() - this.getC().getY() < 80) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 1.8);
                    side_attraction_less(1.8);
                } else if (getRa.getC().getY() - this.getC().getY() < 100) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 2.0);
                    side_attraction_less(2.0);
                } else if (getRa.getC().getY() - this.getC().getY() < 120) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 2.2);
                    side_attraction_less(2.2);
                } else if (getRa.getC().getY() - this.getC().getY() < 140) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 2.4);
                    side_attraction_less(2.4);
                } else if (getRa.getC().getY() - this.getC().getY() < 160) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 2.6);
                    side_attraction_less(2.6);
                } else if (getRa.getC().getY() - this.getC().getY() < 200) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 2.8);
                    side_attraction_less(2.8);
                } else if (getRa.getC().getY() - this.getC().getY() < 240) {
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET / 3.0);
                    side_attraction_less(3.0);
                }
            }
        }
        // collision avec les bords et la raquette
        if (newX < 0 || newX > w - this.getRadius()) {
            this.getDirection().setX(-this.getDirection().getX());
            newX = this.getC().getX() + this.getDirection().getX();
        }
        if (newY < 0 || CollisionR) {
            this.getDirection().setY(-this.getDirection().getY());
            newY = this.getC().getY() + this.getDirection().getY();
            CollisionR = false;
        }
        if (newY > h - this.getRadius()) {
            super.setDelete(true);
        }

        this.setC(new Coordinates(newX, newY));
    }

    // attraction de la balle vers le centre raquette
    public void side_attraction_more(double power) {
        if (getRa.getC().getX() + 100 - this.getC().getX() > 10
                || getRa.getC().getX() + 100 - this.getC().getX() < -10) {
            if (this.getC().getX() < getRa.getC().getX() + 100) {
                this.getDirection().setX(this.getDirection().getX() + 1.2 * GameConstants.POWER_MAGNET / power);
            } else if (this.getC().getX() > getRa.getC().getX() + 100) {
                this.getDirection().setX(this.getDirection().getX() - 1.2 * GameConstants.POWER_MAGNET / power);
            }
        }
    }

    // repulsion de la balle sur les cotes de la raquette
    public void side_attraction_less(double power) {
        if (getRa.getC().getX() + 100 - this.getC().getX() > 10
                || getRa.getC().getX() + 100 - this.getC().getX() < -10) {
            if (this.getC().getX() < getRa.getC().getX() + 100) {
                this.getDirection().setX(this.getDirection().getX() - 1.2 * GameConstants.POWER_MAGNET / power);
            } else if (this.getC().getX() > getRa.getC().getX() + 100) {
                this.getDirection().setX(this.getDirection().getX() + 1.2 * GameConstants.POWER_MAGNET / power);
            }
        }
    }

    public void Affiche_vitesse(double newX, double newY) {
        double testX = newX - this.getC().getX();
        if (testX < basX) {
            basX = (int) testX;
        }
        if (testX > hautX) {
            hautX = (int) testX;
        }
        double testY = newY - this.getC().getY();
        if (testY < basY) {
            basY = (int) testY;
        }
        if (testY > hautY) {
            hautY = (int) testY;
        }
        // System.out.println("basX : " + basX + " basY : " + basY);
        // System.out.println("hautX : " + hautX + " hautY : " + hautY);
        System.out.println("speedX : " + testX + " speedY : " + testY);
    }

    public void setEtat(String e) {
        this.etatBall = e;
    }

    public String getEtat() {
        return etatBall;
    }

    public void setFront(boolean b) {
        this.Front = b;
    }

}
