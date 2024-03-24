package physics.entity;

import entity.Entity;
import entity.EntityColor;
import physics.geometry.Coordinates;
import physics.geometry.Vector;

public abstract class Brick extends Entity {

    private Vector vector;
    private int durability;
    private boolean destroyed;

    private EntityColor color;
    private boolean transparent;
    private boolean unbreakable;

    protected Brick(Coordinates c, int durability, EntityColor color) {
        super(c);
        this.durability = durability;
        this.color = color;
    }

    protected Brick(Coordinates c, int durability) {
        this(c, durability, null);
    }

    protected Brick(Coordinates c, Vector vector, int durability) {
        this(c, durability, null);
        this.vector = vector;
    }

    public void absorb(int damage) {
        durability -= damage;
        if (durability <= 0) {
            durability = 0;
            destroyed = true;
        }
    }

    public int getDurability() {
        return durability;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public EntityColor getColor() {
        return color;
    }

    public void setColor(EntityColor color) {
        this.color = color;
    }

}
