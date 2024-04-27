package physics.geometry;

public class Vector {

    private double x;
    private double y;

    public Vector(Coordinates c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
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

    public void addForce(Force f){
        this.setX(getX()+f.getX());
        this.setY(getY()+f.getY());
    }

    public void substractForce(Force f){
        this.setX(getX()-f.getX());
        this.setY(getY()-f.getY());
    }

    public Vector clone() {
        return new Vector(x, y);
    }

    public void set(Vector other) {
        x = other.x;
        y = other.y;
    }

    public Vector multiply2(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector normalize2(){
        double length = Math.sqrt(x * x + y * y); // Calcul de la longueur du vecteur
        if (length != 0) {
            return new Vector(x/length, y/length);
        }
        return new Vector(0,0);
    }

    public Vector add2(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector substract2(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public double dot(Vector other) {
        return x * other.x + y * other.y;
    }

    public Vector divide2(double scalar) {
        if (scalar != 0) {
            return new Vector(x / scalar, y / scalar);
        }
        return new Vector(0,0);
    }
}
