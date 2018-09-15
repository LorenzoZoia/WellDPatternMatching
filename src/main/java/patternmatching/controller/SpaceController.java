package patternmatching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import patternmatching.model.Point;
import patternmatching.model.Space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/space", produces = "application/json")
public class SpaceController {

    @Autowired
    private Space space;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Point>> getAllPoints() {
        List<Point> pointList = new ArrayList<>();
        pointList.addAll(this.space.getPointList());
        return new ResponseEntity<>(pointList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllPoints(){
        this.space.removeAllPoints();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
