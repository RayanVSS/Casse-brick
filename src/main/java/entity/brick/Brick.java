package entity.brick;

import entity.Entity;
import geometry.*;

public abstract class Brick extends Entity {

    private int durability;
    private boolean destroyed;

    protected Brick(Coordinates c, int durability) {
        super(c);
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

}
