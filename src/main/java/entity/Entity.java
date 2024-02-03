package entity;

import geometry.*;

public abstract class Entity {

    private Coordinates c;

    protected Entity(Coordinates c) {
        this.c = c;
    }

    public Coordinates getC() {
        return c;
    }

    public void setC(Coordinates c) {
        this.c = c;
    }
}
