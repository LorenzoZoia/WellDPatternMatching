package patternmatching.utils;

import patternmatching.model.Point;
import patternmatching.model.Segment;
import patternmatching.model.Space;

import java.util.*;

/**
 * Support Class for calculate the segments in the Space
 */
public class SegmentCalc {

    /**
     * This Method retrieve all the {@link Segment} in the given {@link Space} of a given Length
     *
     * @param space
     * @param length
     * @return
     */
    public static Set<Segment> getAllSegtmentsofLenghtN(final Space space, final Integer length){
        HashSet<Segment> segmentsList = new HashSet<>();
        HashSet<Point> points = (HashSet<Point>) space.getPointList();
        points.forEach(point -> {
            HashSet<Point> pointsSameX = space.getAllPointsWithSameX(point.getX());
            HashSet<Point> pointsSameY = space.getAllPointsWithSameY(point.getY());
            // Checks if there is a Vertical segment of the desired length
            if (pointsSameX.size() >= length) {
                Segment segmentToAdd = new Segment();
                segmentToAdd.addAll(pointsSameX);
                segmentsList.add(segmentToAdd);
            }
            // Checks if there is a Horizontal segment of the desired length
            if (pointsSameY.size() >= length) {
                Segment segmentToAdd = new Segment();
                segmentToAdd.addAll(pointsSameY);
                segmentsList.add(segmentToAdd);
            }
            segmentsList.addAll(checkDiagonalSegments(space,point,length));
        });
        return segmentsList;
    }

    /**
     * Given a Starting Points, this Method returns a list of all the Segments that contains that Point with a Length >= of the given Parameter
     *
     * @param space
     * @param startingPoint
     * @param length
     * @return
     */
    public static List<Segment> checkDiagonalSegments(final Space space, final Point startingPoint, final Integer length){
        List<Segment> segmentList = new ArrayList<>();
        //This Map contains the segments with a given angular coefficient
        HashMap<Float,Segment> coefficientMap = new HashMap<>();
        HashSet<Point> points = (HashSet<Point>) space.getPointList();
        points.forEach(point -> {
            // Since I need only diagonal segments I will ignore points with the same X and Y
            if (point.getX() != startingPoint.getX() && point.getY() != startingPoint.getY()){
                Float m = calculateMCoefficient(startingPoint,point);
                //Add the point to the correct Segment or create a new Segment
                if (coefficientMap.containsKey(m)) {
                    coefficientMap.get(m).addPoint(point);
                }
                else {
                    coefficientMap.put(m,new Segment(point,startingPoint));
                }
            }
        });

        // Check and Return only the segment that satisfy the given length
        coefficientMap.forEach( (m,segment) -> {
            if (segment.getLength() >= length){
                segment.sortSegment();
                segmentList.add(segment);
            }
        });
        return segmentList;
    }

    /**
     * This method, given 2 points, calculates the angular coefficient between them
     *
     * @param firstPoint
     * @param secondPoint
     * @return
     */
    public static Float calculateMCoefficient(final Point firstPoint,final Point secondPoint){
        Float m = (secondPoint.getY() - firstPoint.getY())/(secondPoint.getX() - firstPoint.getX());
        return m;
    }
}
