package geometry;

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

}