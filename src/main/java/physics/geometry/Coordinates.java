package physics.geometry;

public class Coordinates {

    private double x;
    private double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates floorC() {
        return new Coordinates(Math.floor(x), Math.floor(y));
    }

    public int getIntX() {
        return (int) x;
    }

    public int getIntY() {
        return (int) y;
    }

    public Coordinates clone() {
        return new Coordinates(x, y);
    }

    public void add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    public void sub(Vector v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    public void addX(double x) {
        this.x += x;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
}