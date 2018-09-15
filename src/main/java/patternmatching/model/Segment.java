package patternmatching.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

/**
 * This class represent a Segment in a Space
 */
public class Segment {
    /**
     * Listo of all the {@link Point} that compose the Segment
     */
    private ArrayList<Point> segment;

    public Segment(){
        this.segment = new ArrayList<>();
    }

    public Segment(Point firstPoint, Point secondPoint){
        this.segment = new ArrayList<>();
        this.segment.add(secondPoint);
        this.segment.add(firstPoint);
    }

    public void addPoint(Point point){
        this.segment.add(point);
    }

    public void addAll(Collection<Point> points){
        this.segment.addAll(points);
    }

    @JsonIgnore
    public int getLength(){
        return this.segment.size();
    }

    public ArrayList<Point> getSegment() {
        return segment;
    }

    public void sortSegment() {
        Collections.sort(this.segment, Point::compareTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Segment) {
            if(this.segment.equals(((Segment) obj).getSegment())){
                return true;
            }
        }
        return false;
    }
}
