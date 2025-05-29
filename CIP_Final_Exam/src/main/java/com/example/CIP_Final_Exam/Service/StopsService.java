package com.example.CIP_Final_Exam.Service;

import com.example.CIP_Final_Exam.Entity.Stops;
import com.example.CIP_Final_Exam.Repository.StopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopsService {

    @Autowired
    private StopsRepository stopsRepository;

    // CREATE
    public Stops createStops(Stops stops) {
        return stopsRepository.save(stops);
    }

    // READ all stops
    public List<Stops> getAllStops() {
        return stopsRepository.findAll();
    }

    // READ stop by ID
    public Optional<Stops> getStopsById(Long id) {
        return stopsRepository.findById(id);
    }

    // UPDATE stop by ID
    public Optional<Stops> updateStops(Long id, Stops stops) {
        return stopsRepository.findById(id).map(existingStops -> {
            existingStops.setCode(stops.getCode());
            existingStops.setName(stops.getName());
            existingStops.setLatitude(stops.getLatitude());
            existingStops.setLongitude(stops.getLongitude());
            return stopsRepository.save(existingStops);
        });
    }

    // DELETE stop by ID
    public boolean deleteStops(Long id) {
        if (stopsRepository.existsById(id)) {
            stopsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
