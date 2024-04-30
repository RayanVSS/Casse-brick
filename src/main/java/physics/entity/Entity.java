package physics.entity;

import physics.geometry.Coordinates;
import physics.geometry.Vector;

public abstract class Entity {

    private Coordinates c;
    private Vector direction;

    protected Entity(Coordinates c, Vector vector) {
        this.c = c;
        this.direction = vector;
    }

    protected Entity(Coordinates c) {
        this(c, new Vector(new Coordinates(0, 0)));
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

}
