package patternmatching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patternmatching.model.ErrorMessage;
import patternmatching.model.Segment;
import patternmatching.model.Space;
import patternmatching.utils.SegmentCalc;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lines", produces = "application/json")
public class SegmentController {

    @Autowired
    private Space space;

    @RequestMapping(value = "/{n}", method = RequestMethod.GET)
    public ResponseEntity getAllsegmentsWithLengthN(@PathVariable Integer n) {
        if(n > 0) {
            Set<Segment> segmentsToReturn = SegmentCalc.getAllSegtmentsofLenghtN(this.space, n);
            return ResponseEntity.status(HttpStatus.OK).body(segmentsToReturn);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("The length Parameter must be greater than 0"));
        }
    }
}
