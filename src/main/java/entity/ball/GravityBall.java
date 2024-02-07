package entity.ball;


import geometry.Coordinates;
import geometry.Vector;
import gui.GameView;
import javafx.scene.input.KeyCode;
import utils.GameConstants;


public class GravityBall extends Ball{


   public GravityBall(Coordinates c, Vector direction, int vitesse, int d) {
        super(c, direction, vitesse, d);
    }

    public GravityBall(int d) {
        super(d);
    }

       public boolean movement() {
        boolean aux = true;
        double h= GameConstants.DEFAULT_WINDOW_WIDTH;
        double w= GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.direction.getX() * this.vitesse;
        double newY = this.getC().getY() + this.direction.getY() * this.vitesse;

        if(CollisionR) {
            if(GameView.BougePColision) {
                this.direction.setY(-this.direction.getY());
                newY = this.getC().getY() + this.direction.getY() * this.vitesse;
                CollisionR = false;
            }
            if(!GameView.BougePColision) {
                for (KeyCode key : GameView.direction) {
                    switch (key) {
                        case RIGHT:
                        case D:
                            System.out.println("droite");
                            this.direction.setX(1);
                            this.direction.setY(-1);
                            newX = this.getC().getX() + this.direction.getX() * this.vitesse;
                            newY = this.getC().getY() + this.direction.getY() * this.vitesse;
                            CollisionR = false;

                            break;
                        case LEFT:
                        case Q:
                            System.out.println("gauche");
                            this.direction.setX(-1);
                            this.direction.setY(-1);
                            newX = this.getC().getX() + this.direction.getX() * this.vitesse;
                            newY = this.getC().getY() + this.direction.getY() * this.vitesse;
                            CollisionR = false;
                            break;
                    }
                }
            }
        }
        
        if (newX < 0 || newX > h - this.getRadius()) {
            this.direction.setX(-this.direction.getX());
            newX = this.getC().getX() + this.direction.getX() * this.vitesse;
        }
        if (newY < 0 || CollisionR ) {
            this.direction.setY(-this.direction.getY());
            newY = this.getC().getY() + this.direction.getY() * this.vitesse;
            CollisionR = false;
        }
        if (newY > w - this.getRadius()) {
            aux = false;
        }

        
        setGravity();
        this.setC(new Coordinates(newX, newY));
        return aux;
    }

    //Methode qui permet de définir la gravité
    public void setGravity() {
        this.direction.setY(this.direction.getY() + 0.01);
    }






}

