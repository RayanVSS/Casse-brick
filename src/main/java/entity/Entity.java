package entity;

import geometry.*;

public abstract class Entity {

    public abstract Coordinates getC();

    public abstract void setType(int type);

    public abstract int getType();
}
