package physics;

import java.util.ArrayList;

import entity.ball.Ball;
import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private Coordinates c_trajectory ;
    private Vector d_trajectory ;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private int dt_circle = 0;
    public PhysicSetting physics;
    
    public Preview(Ball b, PhysicSetting o){
        this.physics = o;
        this.c_trajectory = new Coordinates(b.getC().getX(), b.getC().getY());
        this.d_trajectory = new Vector(new Coordinates(b.getDirection().getX(), b.getDirection().getY()));
    }

    public Coordinates trajectory(){
        double h = PhysicEngine.DEFAULT_WINDOW_WIDTH;
        double w = PhysicEngine.DEFAULT_WINDOW_HEIGHT;
        double newX = c_trajectory.getX() + d_trajectory.getX() + physics.getWind().getX() + physics.getFrictionRacket().getX();
        double newY = c_trajectory.getY() + d_trajectory.getY() + physics.getWind().getY();
        if (newX < 0 || newX > h - physics.getRadius()) {
            d_trajectory.setX(-d_trajectory.getX());
            newX = c_trajectory.getX() + d_trajectory.getX();
            d_trajectory.setX(d_trajectory.getX()*physics.getRetention());
        }
        if (newY < 0 || newY > w - physics.getRadius()) {
            d_trajectory.setY(-d_trajectory.getY());
            newY = c_trajectory.getY() + d_trajectory.getY();
            d_trajectory.setY(d_trajectory.getY()*physics.getRetention());
        }
        c_trajectory=new Coordinates(newX, newY);
        d_trajectory.add(physics.getWind());
        physics.checkGravity(c_trajectory, d_trajectory);
        //physics.checkFrictionRacket();
        dt_circle++;
        return c_trajectory;
    }

    public void init_path(Ball ball , Pane root){
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

     public void add_circle(Pane root){
        if(PhysicEngine.PATH){
            Coordinates c = trajectory();
            if (dt_circle>=5) {
                dt_circle=0;
                Circle c1 = new Circle(c.getX(), c.getY(), 2);
                c1.setFill(Color.BLUE);
                root.getChildren().add(c1);
                circles.add(c1);
                root.getChildren().remove(circles.get(0));
                circles.remove(0);
            }
        }
    }
}
