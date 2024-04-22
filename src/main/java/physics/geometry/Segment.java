package physics.geometry;

public class Segment {
    private Coordinates start;
    private Coordinates end;

    public Segment(Coordinates start, Coordinates end) {
        this.start = start;
        this.end = end;
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

    
}
