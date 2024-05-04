package physics.gui;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import physics.AppPhysic;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;

/***************************************************************************
 *                  Explication de classe Preview  :  
 * 
 * Cette classe contient les informations pour la trajectoire de la balle
 * 
 * @var Coordinates c_trajectory : les coordonnées de la trajectoire
 * @var Vector d_trajectory : le vecteur de direction de la trajectoire
 * @var ArrayList<Circle> circles : les points pour la trajectoire
 * @var int dt_circle : le temps entre chaque point
 * @var physics physics : les informations de la simulation
 * 
 * @fonction init_path : initialisation de la trajectoire
 * @fonction add_circle : ajout d'une nouvelle position pour la trajectoire
 * 
 * @author Ilias Bencheikh 
 **************************************************************************/

public class Preview {

    private Pane root;
    private Ball ball;

    private Coordinates c_trajectory ;
    private Vector d_trajectory ;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private int dt_circle = 0;
    public PhysicSetting physics;
    public Coordinates path;

    public boolean CollisionR = false;
    
    public Preview(Ball b, PhysicSetting o, Pane root){
        this.physics = o;
        this.root = root;
        this.ball = b;
        this.c_trajectory = new Coordinates(b.getC().getX(), b.getC().getY());
        this.d_trajectory = new Vector(new Coordinates(b.getDirection().getX(), b.getDirection().getY()));
    }

    public void trajectory(){
        double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double w = PhysicSetting.DEFAULT_WINDOW_WIDTH;
        double newX = c_trajectory.getX() + d_trajectory.getX() * ball.getSpeed() ;
        double newY = c_trajectory.getY() + d_trajectory.getY() * ball.getSpeed() ;
        if (CollisionR) {
            d_trajectory.setY(-d_trajectory.getY()+(ball.getRotation().getEffect()/90)*d_trajectory.getY());
            newY = c_trajectory.getY() + d_trajectory.getY()*ball.getSpeed();
            CollisionR = false;
        }
        if (newX <PhysicEngine.start_border || newX > w - ball.getRadius()) {
            d_trajectory.setX(-d_trajectory.getX());
            d_trajectory.setY(d_trajectory.getY()+(ball.getRotation().getEffect()/90)*d_trajectory.getY());
            newX = c_trajectory.getX() + d_trajectory.getX()*ball.getSpeed();
            d_trajectory.setX(d_trajectory.getX()*physics.getRetention());
        }
        if (newY < 0 || newY > h - ball.getRadius()) {
            d_trajectory.setY(-d_trajectory.getY()+(ball.getRotation().getEffect()/90)*d_trajectory.getY());
            newY = c_trajectory.getY() + d_trajectory.getY()*ball.getSpeed();
            d_trajectory.setY(d_trajectory.getY()*physics.getRetention());
        } 
        c_trajectory=new Coordinates(newX, newY);
        d_trajectory.add(physics.getWind());
        physics.checkGravity(c_trajectory, d_trajectory);
        dt_circle++;
    }

    public void init_path(){
        if(PhysicEngine.PATH){
            clear_path(root);
            c_trajectory = new Coordinates(ball.getC().getX(), ball.getC().getY());
            d_trajectory = new Vector(new Coordinates(ball.getDirection().getX(), ball.getDirection().getY()));
            dt_circle = 0;
            for(int i = 0; i<50; i++){
                trajectory();
                if(i%5==0){
                    Circle c = new Circle(c_trajectory.getX(), c_trajectory.getY(), 2);
                    c.setFill(Color.BLUE);
                    root.getChildren().add(c);
                    circles.add(c);
                }
            }
        }
    }

    public void clear_path(Pane root){
        if(PhysicEngine.PATH){
            for(Circle c: circles){
                root.getChildren().remove(c);
            }
            circles.clear();
        }
    }

    public void add_circle(){
        if(PhysicEngine.PATH){
            if (dt_circle>=5) {
                dt_circle=0;
                Circle c1 = new Circle(c_trajectory.getX(), c_trajectory.getY(), 2);
                c1.setFill(Color.BLUE);
                root.getChildren().add(c1);
                circles.add(c1);
                root.getChildren().remove(circles.get(0));
                path = new Coordinates(circles.get(0).getCenterX(), circles.get(0).getCenterY());
                circles.remove(0);
            }
        }
    }

    public void check(){
        if(path!=null && dt_circle==0 && ((ball.getC().getX()>path.getX()+1 || ball.getC().getX()<path.getX()-1 || ball.getC().getY()>path.getY()+1 || ball.getC().getY()<path.getY()-1))){
            init_path();
            path = null;
        }
    }

    public void update(Racket r){
        if (PhysicEngine.PATH && physics.checkCollisionRacket(r, c_trajectory, d_trajectory)) {
            CollisionR = true;
        }
    }

    public int getdt(){
        return dt_circle;
    }

    public void checkCollisionOtherBall(Ball b){
        if(ball.equals(b)){
            return;
        }
        // gestion de la collision entre les balles en X
        boolean collisionX1 = c_trajectory.getX()+ball.getRadius()>=b.getC().getX()-b.getRadius() && c_trajectory.getX()-ball.getRadius()<b.getC().getX()+b.getRadius();
        boolean collisionX2 = b.getX()+b.getRadius()>=c_trajectory.getX()-ball.getRadius() && b.getX()-b.getRadius()<c_trajectory.getX()+ball.getRadius();
        boolean collisionXY = (c_trajectory.getY()+ball.getRadius()>=b.getY()-b.getRadius() && c_trajectory.getY()-ball.getRadius()<b.getY()+b.getRadius()) || (b.getY()+b.getRadius()>=c_trajectory.getY()-ball.getRadius() && b.getY()-b.getRadius()<c_trajectory.getY()+ball.getRadius());
        // gestion de la collision entre les balles en Y
        boolean collisionY1 = c_trajectory.getY()+ball.getRadius()>=b.getC().getY()-b.getRadius() && c_trajectory.getY()-ball.getRadius()<b.getC().getY()+b.getRadius() ;
        boolean collisionY2 = b.getY()+b.getRadius()>=c_trajectory.getY()-ball.getRadius() && b.getY()-b.getRadius()<c_trajectory.getY()+ball.getRadius() ;
        boolean collisionYX = (c_trajectory.getX()+ball.getRadius()>=b.getX()-b.getRadius() && c_trajectory.getX()-ball.getRadius()<b.getX()+b.getRadius()) || (b.getX()+b.getRadius()>=c_trajectory.getX()-ball.getRadius() && b.getX()-b.getRadius()<c_trajectory.getX()+ball.getRadius());
        if((collisionX1||collisionX2) && collisionXY){
            if(d_trajectory.getX() >= 0 && b.getDirection().getX() <= 0){
                d_trajectory.setX(b.getDirection().getX());
            }
            else if(d_trajectory.getX() >= 0 && b.getDirection().getX() >= 0){
                d_trajectory.setX(-d_trajectory.getX());
            }
            else if(d_trajectory.getX() <= 0 && b.getDirection().getX() <= 0){
                d_trajectory.setX(Math.max(d_trajectory.getX(), b.getDirection().getX()));
            }
        }
        else if((collisionY1 || collisionY2) && collisionYX){
            if(d_trajectory.getY() >= 0 && b.getDirection().getY() <= 0){
                d_trajectory.setY(b.getDirection().getY());
            }
            else if(d_trajectory.getY() >= 0 && b.getDirection().getY() >= 0){
                d_trajectory.setY(-d_trajectory.getY());
            }
            else if(d_trajectory.getY() <= 0 && b.getDirection().getY() <= 0){
                d_trajectory.setY(Math.max(d_trajectory.getY(), b.getDirection().getY()));
            }
        }
    }

    public void checkCollisionBrick(Brick b){
        
    }

    public static ArrayList<Circle> preview_no_effect(Ball b , Pane root){
        ArrayList<Circle> circles = new ArrayList<Circle>();
        int compt = 0;
        Coordinates c = new Coordinates(b.getC().getX(), b.getC().getY());
        Vector d = new Vector(new Coordinates(b.getDirection().getX(), b.getDirection().getY()));
        if(b.getPreview()!=null){
            compt = b.getPreview().getdt()-1;
        }
        for(int i = compt; i<50; i++){
            double h = PhysicSetting.DEFAULT_WINDOW_HEIGHT;
            double w = PhysicSetting.DEFAULT_WINDOW_WIDTH;
            double newX = c.getX() + d.getX() * b.getSpeed() ;
            double newY = c.getY() + d.getY() * b.getSpeed() ;
            if (newX < 0 || newX > w - b.getRadius()) {
                d.setX(-d.getX());
                newX = c.getX() + d.getX()*b.getSpeed();
            }
            if (newY < 0 || newY > h - b.getRadius()) {
                d.setY(-d.getY());
                newY = c.getY() + d.getY()*b.getSpeed();
            } 
            c=new Coordinates(newX, newY);
            AppPhysic.physics.checkGravity(c, d);
            if(i%5==0){
                Circle c1 = new Circle(c.getX(), c.getY(), 2);
                c1.setFill(Color.RED);
                circles.add(c1);
                root.getChildren().add(c1);
            }

        }
        return circles;
    }

}
