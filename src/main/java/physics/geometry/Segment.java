package physics.geometry;

/**
 * Classe qui permet de gerer les segments
 */
public class Segment {
    private Coordinates start;
    private Coordinates end;

    public Segment(Coordinates start, Coordinates end) {
        this.start = start;
        this.end = end;
    }

    public Segment(double x1, double y1, double x2, double y2) {
        this(new Coordinates(x1, y1), new Coordinates(x2, y2));
    }

    public Coordinates getStart() {
        return this.start;
    }

    public Coordinates getEnd() {
        return this.end;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.end.getX() - this.start.getX(), 2) + Math.pow(this.end.getY() - this.start.getY(), 2));
    }

    public double getAngle() {
        return Math.atan2(this.end.getY() - this.start.getY(), this.end.getX() - this.start.getX());
    }

    public boolean intersect(Coordinates point) {
        return point.getX() >= Math.min(this.start.getX(), this.end.getX())
                && point.getX() <= Math.max(this.start.getX(), this.end.getX())
                && point.getY() >= Math.min(this.start.getY(), this.end.getY())
                && point.getY() <= Math.max(this.start.getY(), this.end.getY());
    }

    public boolean intersect(Segment segment) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = segment.getStart().getX();
        double y3 = segment.getStart().getY();
        double x4 = segment.getEnd().getX();
        double y4 = segment.getEnd().getY();

        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0) {
            return false;
        }

        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

        return xi >= Math.min(x1, x2) && xi <= Math.max(x1, x2) && xi >= Math.min(x3, x4) && xi <= Math.max(x3, x4)
                && yi >= Math.min(y1, y2) && yi <= Math.max(y1, y2) && yi >= Math.min(y3, y4) && yi <= Math.max(y3, y4);
    } 

    public boolean intersect(Coordinates c,double longueur,double largeur){
        return c.getX() >= Math.min(this.start.getX(), this.end.getX())-longueur
                && c.getX() <= Math.max(this.start.getX(), this.end.getX())+longueur
                && c.getY() >= Math.min(this.start.getY(), this.end.getY())-largeur
                && c.getY() <= Math.max(this.start.getY(), this.end.getY())+largeur;
    }

    public void deplace(Vector v) {
        this.start.add(v);
        this.end.add(v);
    }

    public void addX(double x) {
        this.start.addX(x);
        this.end.addX(x);
    }
}
