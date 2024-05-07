package physics.geometry;

import java.util.ArrayList;

public abstract class Figure {

    public String id;
    public ArrayList<Segment> segments;

    public Figure() {
        segments = new ArrayList<>();
    }

    public void addSegment(Segment s) {
        segments.add(s);
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setS(ArrayList<Segment> segments) {
        this.segments = segments;
    }



}
