package com.example.CIP_Final_Exam.Controller;

import com.example.CIP_Final_Exam.Entity.Routes;
import com.example.CIP_Final_Exam.Entity.Stops;
import com.example.CIP_Final_Exam.Entity.Trips;
import com.example.CIP_Final_Exam.Service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
public class TripsController {
    @Autowired
    private TripsService tripsService;

    @PostMapping
    public ResponseEntity<Trips> createTrip(@RequestBody Trips trip) {
        Trips createdTrip = tripsService.createTrips(trip);
        return ResponseEntity.ok(createdTrip);
    }

    @GetMapping
    public ResponseEntity<List<Trips>> getAllTrips() {
        return ResponseEntity.ok(tripsService.getAllTrips());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trips> getTripById(@PathVariable Long id) {
        Optional<Trips> trip = tripsService.getTripsById(id);
        return trip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trips> updateTrip(@PathVariable Long id, @RequestBody Trips trip) {
        Optional<Trips> updatedTrip = tripsService.updateTrips(id, trip);
        return updatedTrip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byRouteAndDeparture")
    public ResponseEntity<List<Trips>> findByRouteAndDepartureTime(
            @RequestParam Long routeId,
            @RequestParam String departureTime) {

        Routes route = new Routes();
        route.setId(routeId);

        LocalDate departure = LocalDate.parse(departureTime);
        List<Trips> trips = tripsService.findByRouteAndDepartureTime(route, departure);
        return ResponseEntity.ok(trips);
    }

    @PostMapping("/byStopsAndDeparture")
    public ResponseEntity<List<Trips>> findByStopsWithinDepartureTime(
            @RequestBody List<Stops> stops,
            @RequestParam String departureTime) {

        LocalDate departure = LocalDate.parse(departureTime);
        List<Trips> trips = tripsService.findByStopsWithinDepartureTime(stops, departure);
        return ResponseEntity.ok(trips);
    }


    @PutMapping("/updateArrivalTime")
    public ResponseEntity<Void> updateArrivalTimeByBusNumber(
            @RequestParam String arrivalTime,
            @RequestParam int tripId) {

        LocalDate arrival = LocalDate.parse(arrivalTime);
        tripsService.updateArrivalTimeByTripId(arrival, (long) tripId);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/updateDepartureTime")
    public ResponseEntity<Void> updateDepartureTime(
            @RequestParam String departureTime,
            @RequestParam int tripId) {

        LocalDate departure = LocalDate.parse(departureTime);
        tripsService.updateDepartureTimeByTripId(departure, (long) tripId);
        return ResponseEntity.ok().build();
    }

}
