package entity.brick;

import entity.Entity;
import geometry.*;

public class Brick extends Entity {

    private int durability;

    public Brick(Coordinates c) {
        super(c);
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

}
