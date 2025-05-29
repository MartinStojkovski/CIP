package com.example.CIP_Final_Exam.Service;

import com.example.CIP_Final_Exam.Entity.Stops;
import com.example.CIP_Final_Exam.Entity.Trips;
import com.example.CIP_Final_Exam.Entity.Routes;
import com.example.CIP_Final_Exam.Repository.TripsRepository;
import com.example.CIP_Final_Exam.Repository.RoutesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripsService {

    @Autowired
    private TripsRepository tripsRepository;

    @Autowired
    private RoutesRepository routesRepository;


    // UPDATE arrival time for a specific trip
    public Optional<Trips> updateArrivalTimeByTripId(LocalDate arrivalTime, Long tripId) {
        return tripsRepository.findById(tripId).map(trip -> {
            trip.setArrivalTime(arrivalTime);
            return tripsRepository.save(trip);
        });
    }

    // UPDATE departure time for a specific trip
    public Optional<Trips> updateDepartureTimeByTripId(LocalDate departureTime, Long tripId) {
        return tripsRepository.findById(tripId).map(trip -> {
            trip.setDepartureTime(departureTime);
            return tripsRepository.save(trip);
        });
    }

    // FIND trips by route and departure time
    public List<Trips> findByRouteAndDepartureTime(Routes route, LocalDate departureTime) {
        return tripsRepository.findByRouteAndDepartureTime(route, departureTime);
    }

    // FIND trips by a list of stops and departure time
    public List<Trips> findByStopsWithinDepartureTime(List<Stops> stops, LocalDate departureTime) {
        return tripsRepository.findByStopsWithinDepartureTime(stops, departureTime);
    }

    // CREATE a new trip
    public Trips createTrips(Trips trips) {
        return tripsRepository.save(trips);
    }

    // READ all trips
    public List<Trips> getAllTrips() {
        return tripsRepository.findAll();
    }

    // READ trip by ID
    public Optional<Trips> getTripsById(Long id) {
        return tripsRepository.findById(id);
    }

    // UPDATE trip by ID
    public Optional<Trips> updateTrips(Long id, Trips trips) {
        return tripsRepository.findById(id).map(existingTrips -> {
            existingTrips.setDepartureTime(trips.getDepartureTime());
            existingTrips.setArrivalTime(trips.getArrivalTime());
            existingTrips.setBusNumber(trips.getBusNumber());

            return tripsRepository.save(existingTrips);
        });
    }
}
