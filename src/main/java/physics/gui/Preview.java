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
import java.util.Set;
import physics.geometry.Segment;

import config.Map;

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
        for(Segment s:PhysicEngine.LIMIT_SIMULATION){
            if(Ball.checkCollision(c_trajectory, d_trajectory, ball.getRadius(), s, null)){
                break;
            }
        }
        double newX = c_trajectory.getX() + d_trajectory.getX() * ball.getSpeed() ;
        double newY = c_trajectory.getY() + d_trajectory.getY() * ball.getSpeed() ;
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
        double distance = ball.getRadius() + b.getRadius();
    
        if (Math.abs(this.c_trajectory.getX() - ball.getX()) < distance &&
            Math.abs(this.c_trajectory.getX() - ball.getY()) < distance) {
    
            double dx = this.c_trajectory.getX() - ball.getX();
            double dy = this.c_trajectory.getY() - ball.getY();
            distance = Math.sqrt(dx * dx + dy * dy);
            
            double angle = Math.atan2(dy, dx);
            double sin = Math.sin(angle);
            double cos = Math.cos(angle);
    
            double vy1 = this.d_trajectory.getY() * cos - this.d_trajectory.getX() * sin;
            double vx2 = b.getDirection().getX() * cos + b.getDirection().getY() * sin;
    
            this.d_trajectory.setX(vx2 * cos - vy1 * sin);
            this.d_trajectory.setY(vy1 * cos + vx2 * sin);

        }
    }

    public void checkCollisionBrick(Set<Brick> bricks){
        for(Brick b: bricks){
            Ball.checkCollision(c_trajectory, d_trajectory, ball.getRadius(), b,null);
        }
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
