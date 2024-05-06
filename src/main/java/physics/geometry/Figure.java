package physics.geometry;

import physics.entity.Entity;
import java.util.ArrayList;

public abstract class Figure {

    public String id;
    private ArrayList<Segment> segments;

    public Figure() {
        segments = new ArrayList<>();
    }

    public abstract boolean collision(Entity l);
    
    public abstract double getArea();

    public abstract double getPerimeter();
    
    public abstract void move(double dx, double dy);

    public abstract void rotate(double angle);

    public abstract void scale(double factor);

    public String getId() {
        return id;
    }

}
