package patternmatching.model;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains all the {@link Point}
 */
@Repository
public class Space {
    /**
     * This Set contains all the points of the current space
     */
    private HashSet<Point> pointList;
    /**
     * This HashMap contains for every coordinate X, all the points in the space with the same Coordinate
     */
    private HashMap<Float,HashSet<Point>> sameXCoordinatePoints;
    /**
     * This HashMap contains for every coordinate Y, all the points in the space with the same Coordinate
     */
    private HashMap<Float,HashSet<Point>> sameYCoordinatePoints;

    public Space(){
        this.pointList = new HashSet<>();
        this.sameXCoordinatePoints = new HashMap<>();
        this.sameYCoordinatePoints = new HashMap<>();
    }

    public Set<Point> getPointList() {
        return pointList;
    }

    public void addPoint(Point pointToAdd){
        this.pointList.add(pointToAdd);
        Float x = pointToAdd.getX();
        Float y = pointToAdd.getY();

        if(this.sameXCoordinatePoints.containsKey(x)){
            this.sameXCoordinatePoints.get(x).add(pointToAdd);
        }
        else {
            HashSet<Point> setToAdd = new HashSet<>();
            setToAdd.add(pointToAdd);
            this.sameXCoordinatePoints.put(x,setToAdd);
        }

        if(this.sameYCoordinatePoints.containsKey(y)){
            this.sameYCoordinatePoints.get(y).add(pointToAdd);
        }
        else {
            HashSet<Point> setToAdd = new HashSet<>();
            setToAdd.add(pointToAdd);
            this.sameYCoordinatePoints.put(y,setToAdd);
        }
    }

    /**
     * This method returns a Set of {@link Point} with the Same x Coordinate
     * @param x
     * @return the set of Points with the same coordinate, or an empty set if there are not point with that X
     */
    public HashSet<Point> getAllPointsWithSameX(float x){
        if (this.sameXCoordinatePoints.containsKey(x)){
            return this.sameXCoordinatePoints.get(x);
        }
        else {
            return new HashSet<>();
        }
    }

    /**
     * This method returns a Set of {@link Point} with the Same Y Coordinate
     * @param y
     * @return the set of Points with the same coordinate, or an empty set if there are not point with that Y
     */
    public HashSet<Point> getAllPointsWithSameY(float y){
        if (this.sameYCoordinatePoints.containsKey(y)){
            return this.sameYCoordinatePoints.get(y);
        }
        else {
            return new HashSet<>();
        }
    }

    public void removeAllPoints(){
        this.pointList = new HashSet<>();
        this.sameXCoordinatePoints = new HashMap<>();
        this.sameYCoordinatePoints = new HashMap<>();
    }
}
