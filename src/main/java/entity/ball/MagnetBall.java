package entity.ball;

import entity.racket.MagnetRacket;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import utils.GameConstants;


/*
 * Alors si tu lis cette phrase c'est que tu dois modifier ou comprendre le code BONNE CHANCE!
 */

public class MagnetBall extends Ball {
    //etat de la Raquette
    private static String etatRacket = MagnetRacket.getEtat();
    //etat de la balle
    private String etatBall = "positif";
    //si la balle est devant la raquette
    private boolean Front = false;
    //coordonnées de la raquette
    public static Coordinates getRa = new Coordinates(0, 0);

    public MagnetBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES, GameConstants.DEFAULT_BALL_START_DIRECTION,
        GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setWindow(GameConstants.DEFAULT_GAME_ROOT_WIDTH,GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    public MagnetBall(int d) {
        super(d);
    }

    @Override
    public boolean movement() {
        boolean lost = true;
        double w = GameConstants.DEFAULT_GAME_ROOT_WIDTH;
        double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.getDirection().getX();
        double newY = this.getC().getY() + this.getDirection().getY();
        //verifie si la balle est devant la raquette avant n'importe action 
        if(Front){
            //actualise l'etat de la raquette
            etatRacket = MagnetRacket.getEtat();
            //si l'etat de la raquette est la meme que la balle
            if (etatRacket == etatBall) {
                if(!CollisionR){
                    //attraction de la balle vers la raquette qui est moin puissante si la balle est loin de la raquette
                    if(getRa.getY()-this.getC().getY() < 10){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET);
                        side_attraction_more(1.0);
                    }else if (getRa.getY()-this.getC().getY()  < 20){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/1.2);
                        side_attraction_more(1.2);
                    }else if (getRa.getY()-this.getC().getY()  < 40){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/1.4);
                        side_attraction_more(1.4);
                    }else if (getRa.getY()-this.getC().getY()  < 60){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/1.6);
                        side_attraction_more(1.6);
                    }else if (getRa.getY()-this.getC().getY()  < 80){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/1.8);
                        side_attraction_more(1.8);
                    } else if(getRa.getY()-this.getC().getY()  < 100){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/2.0);
                        side_attraction_more(2.0);
                    } else if(getRa.getY()-this.getC().getY()  < 120){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/2.2);
                        side_attraction_more(2.2);
                    }else if(getRa.getY()-this.getC().getY()  < 140){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/2.4);
                        side_attraction_more(2.4);
                    }else if(getRa.getY()-this.getC().getY()  < 160){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/2.6);
                        side_attraction_more(2.6);
                    }else if(getRa.getY()-this.getC().getY() < 200){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/2.8);
                        side_attraction_more(2.8);
                    }else if(getRa.getY()-this.getC().getY() < 240){
                        this.getDirection().setY(this.getDirection().getY() + GameConstants.POWER_MAGNET/3.0);
                        side_attraction_more(3.0);
                    }
                }else{
                    //si la balle est en collision avec la raquette alors la balle vas au millieu de la raquette et ne bouge plus 
                    this.setC(new Coordinates(getRa.getX()+99, getRa.getY()-10));
                    this.getDirection().setX(0);
                    this.getDirection().setY(1);
                    CollisionR = true;
                    return true;
                }
            }
            //si l'etat de la raquette est different de la balle
            if (etatRacket != etatBall) {     
                //repulsion de la balle de la raquette qui est moin puissante si la balle est loin de la raquette     
                if(getRa.getY()-this.getC().getY() < 10){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET);
                    side_attraction_less(1.0);
                }else if (getRa.getY()-this.getC().getY()  < 20){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/1.2);
                    side_attraction_less(1.2);
                }else if (getRa.getY()-this.getC().getY()  < 40){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/1.4);
                    side_attraction_less(1.4);
                }else if (getRa.getY()-this.getC().getY()  < 60){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/1.6);
                    side_attraction_less(1.6);
                }else if (getRa.getY()-this.getC().getY()  < 80){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/1.8);
                    side_attraction_less(1.8);
                } else if(getRa.getY()-this.getC().getY()  < 100){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/2.0);
                    side_attraction_less(2.0);
                } else if(getRa.getY()-this.getC().getY()  < 120){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/2.2);
                    side_attraction_less(2.2);
                }else if(getRa.getY()-this.getC().getY()  < 140){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/2.4);
                    side_attraction_less(2.4);
                }else if(getRa.getY()-this.getC().getY()  < 160){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/2.6);
                    side_attraction_less(2.6);
                }else if(getRa.getY()-this.getC().getY() < 200){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/2.8);
                    side_attraction_less(2.8);
                }else if(getRa.getY()-this.getC().getY() < 240){
                    this.getDirection().setY(this.getDirection().getY() - GameConstants.POWER_MAGNET/3.0);
                    side_attraction_less(3.0);
                }
            }
        }
        //collision avec les bords et la raquette
        if (newX < 0 || newX > w - this.getRadius()) {
            this.getDirection().setX(-this.getDirection().getX());
            newX = this.getC().getX() + this.getDirection().getX() ;
        }
        if (newY < 0 || CollisionR) {
            this.getDirection().setY(-this.getDirection().getY());
            newY = this.getC().getY() + this.getDirection().getY() ;
            CollisionR = false;
        }
        if (newY > h - this.getRadius()) {
            lost = false;
        }

        this.setC(new Coordinates(newX, newY));
        return lost;
    }


    //attraction de la balle vers le centre raquette 
    public void side_attraction_more(double power){
        if(getRa.getX()+100 - this.getC().getX()>10 || getRa.getX()+100 - this.getC().getX()<-10){
        if (this.getC().getX() < getRa.getX()+100){
            this.getDirection().setX(this.getDirection().getX() + 1.2*GameConstants.POWER_MAGNET/power);
        }else if (this.getC().getX() > getRa.getX()+100){
            this.getDirection().setX(this.getDirection().getX() - 1.2*GameConstants.POWER_MAGNET/power);
        }
        }
    }

    //repulsion de la balle sur les cotes de la raquette
    public void side_attraction_less(double power){
        if(getRa.getX()+100 - this.getC().getX()>10 || getRa.getX()+100 - this.getC().getX()<-10){
            if (this.getC().getX() < getRa.getX()+100){
                this.getDirection().setX(this.getDirection().getX() - 1.2*GameConstants.POWER_MAGNET/power);
            }else if (this.getC().getX() > getRa.getX()+100){
                this.getDirection().setX(this.getDirection().getX() + 1.2*GameConstants.POWER_MAGNET/power);
            }
            }
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
