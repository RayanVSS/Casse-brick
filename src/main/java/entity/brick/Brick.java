package entity.brick;

import entity.Entity;
import geometry.Coordinates;

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

    public boolean isDestroyed() {
        return destroyed;
    }

    public void absorb(int damage) {
        durability -= damage;
        if (durability <= 0) {
            durability = 0;
            destroyed = true;
        }
    }
}
