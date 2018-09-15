package patternmatching.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Class used in order to represent a {@link Point} in a {@link Space}
 */
@Component
public class Point implements Comparable<Point>{

    @NotNull
    private float x;

    @NotNull
    private float y;

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point){
            if(this.x == ((Point)obj).getX() && this.y == ((Point)obj).getY()){
                return true;
            }
            else {
                return false;
            }
        }
        else {
           return false;
        }
    }


    @Override
    public int compareTo(Point o) {
        int xCompare = (int) (this.x - o.getX());
        if (xCompare == 0) {
            return (int) (this.y - o.getY());
        }
        else {
            return xCompare;
        }
    }
}
