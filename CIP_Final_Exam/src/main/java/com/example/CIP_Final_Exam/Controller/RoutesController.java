package com.example.CIP_Final_Exam.Controller;
import com.example.CIP_Final_Exam.Entity.Routes;
import com.example.CIP_Final_Exam.Service.RoutesService;
import com.example.CIP_Final_Exam.Repository.RoutesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RoutesController {
    private Logger logger = LoggerFactory.getLogger(RoutesController.class);

    @Autowired
    private RoutesService routesService;

    @Autowired
    private RoutesRepository routesRepository;

    @GetMapping
    public List<Routes> getAllRoutes() {
        return routesService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public Optional<Routes> getRoutesById(@PathVariable Long id) {
        return routesService.getRoutesById(id);
    }

    @PostMapping("/addRoute")
    public Routes createRoutes(@RequestBody Routes routes) {
        return routesService.createRoutes(routes);
    }

    @PutMapping("/updateRoute/{id}")
    public Optional<Routes> updateRoutes(@PathVariable Long id, @RequestBody Routes routes) {
        return routesService.updateRoutes(id, routes);
    }

    @DeleteMapping("/deleteRoute/{id}")
    public ResponseEntity<String> deleteRoutes(@PathVariable Long id) {
        routesService.deleteRoutes(id);
        return ResponseEntity.ok("Route deleted successfully.");
    }
}
