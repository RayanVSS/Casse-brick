package physics.geometry;

import entity.Entity;

public abstract class Figure {

    public String id;

    public Figure() {

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
