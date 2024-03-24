package entity.brick;

import physics.entity.Brick;
import physics.geometry.Coordinates;
import physics.geometry.Vector;

public class BrickClassic extends Brick {

    public static final int BASE_DURABILITY = 100;

    public BrickClassic(Coordinates c) {
        super(c, BASE_DURABILITY);
    }

    public BrickClassic(Coordinates c, Vector vector) {
        super(c, vector, BASE_DURABILITY);
    }
}
