package geometry;

public class Vector {

    private double x;
    private double y;

    public Vector(Coordinates c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void add(Vector other) {
        x += other.x;
        y += other.y;
    }

    public void subtract(Vector other) {
        x -= other.x;
        y -= other.y;
    }

    public void multiply(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public void divide(double scalar) {
        if (scalar != 0) {
            x /= scalar;
            y /= scalar;
        }
    }

    public void normalize() {
        double length = Math.sqrt(x * x + y * y); // Calcul de la longueur du vecteur
        if (length != 0) {
            x /= length; // Redimensionnement de la composante x
            y /= length; // Redimensionnement de la composante y
        }
    }
}
