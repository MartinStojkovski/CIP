package com.example.CIP_Final_Exam.Controller;
import com.example.CIP_Final_Exam.Entity.Stops;
import com.example.CIP_Final_Exam.Service.StopsService;
import com.example.CIP_Final_Exam.Repository.StopsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stops")
public class StopsController {

    private Logger logger = LoggerFactory.getLogger(StopsController.class);

    @Autowired
    private StopsService stopsService;

    @Autowired
    private StopsRepository stopsRepository;

    @GetMapping("/")
    public ResponseEntity<List<Stops>> getAllStops() {
        List<Stops> stops = stopsService.getAllStops();
        return ResponseEntity.ok(stops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Stops>> getStopsById(@PathVariable Long id) {
        Optional<Stops> stops = stopsService.getStopsById(id);
        return ResponseEntity.ok(stops);
    }

    @PutMapping("/updateStop/{id}")
    public ResponseEntity<Optional<Stops>> updateStops(@PathVariable Long id, @RequestBody Stops stops) {
        Optional<Stops> updatedStops = stopsService.updateStops(id, stops);
        return ResponseEntity.ok(updatedStops);
    }

    @DeleteMapping("/deleteStop/{id}")
    public ResponseEntity<String> deleteStops(@PathVariable Long id) {
        stopsService.deleteStops(id);
        return ResponseEntity.ok("Stop deleted successfully.");
    }
}
