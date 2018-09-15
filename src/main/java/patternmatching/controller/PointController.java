package patternmatching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import patternmatching.model.Point;
import patternmatching.model.Space;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/point", produces = "application/json")
public class PointController {

    @Autowired
    private Space space;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Point> addPoint(@Valid @RequestBody Point point, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.space.addPoint(point);
        return new ResponseEntity<>(point,HttpStatus.CREATED);
    }

}
