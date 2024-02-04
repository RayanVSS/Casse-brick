package entity.brick;

import geometry.Coordinates;

public class BrickUnbreakable extends Brick {

    public static final int BASE_DURABILITY = -1;

    public BrickUnbreakable(Coordinates c) {
        super(c, BASE_DURABILITY);
    }

    @Override
    public void absorb(int damage) {
        // N'absorbe pas
    }
}