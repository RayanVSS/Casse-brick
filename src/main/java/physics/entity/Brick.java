package physics.entity;

import entity.EntityColor;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;
import physics.geometry.Segment;
import java.util.ArrayList;

public abstract class Brick extends Entity {

    private int durability;

    private EntityColor color;
    private boolean transparent;
    private boolean unbreakable;
    private ArrayList<Segment> segments = new ArrayList<>();

    protected Brick(Coordinates c, int durability, EntityColor color) {
        super(c);
        this.durability = durability;
        this.color = color;
        createsegments();
    }

    public Brick(Coordinates c, int durability) {
        this(c, durability, null);
        createsegments();
    }

    protected Brick(Coordinates c, Vector vector, int durability) {
        this(c, durability, null);
        setDirection(vector);
        createsegments();
    }

    public void absorb(int damage) {
        durability -= damage;
        if (durability <= 0 && !unbreakable) {
            durability = 0;
            setDestroyed(true);
        }
    }

    public int getDurability() {
        return durability;
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

    public boolean contains(double x, double y) {
        return x >= getC().getX() && x <= getC().getX() + GameConstants.BRICK_WIDTH &&
                y >= getC().getY() && y <= getC().getY() + GameConstants.BRICK_HEIGHT;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void createsegments() {
        segments.clear();
        segments.add(
                new Segment(getC().getX(), getC().getY(), getC().getX() + GameConstants.BRICK_WIDTH, getC().getY()));
        segments.add(new Segment(getC().getX() + GameConstants.BRICK_WIDTH, getC().getY(),
                getC().getX() + GameConstants.BRICK_WIDTH, getC().getY() + GameConstants.BRICK_HEIGHT));
        segments.add(new Segment(getC().getX() + GameConstants.BRICK_WIDTH, getC().getY() + GameConstants.BRICK_HEIGHT,
                getC().getX(), getC().getY() + GameConstants.BRICK_HEIGHT));
        segments.add(
                new Segment(getC().getX(), getC().getY() + GameConstants.BRICK_HEIGHT, getC().getX(), getC().getY()));
    }

    public void deplace(Vector v) {
        for (Segment s : segments) {
            s.deplace(v);
        }
    }
}
