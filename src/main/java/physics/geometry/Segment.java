package physics.geometry;

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

    public void deplace(Vector v) {
        this.start.add(v);
        this.end.add(v);
    }

    public void addX(double x) {
        this.start.addX(x);
        this.end.addX(x);
    }
}
