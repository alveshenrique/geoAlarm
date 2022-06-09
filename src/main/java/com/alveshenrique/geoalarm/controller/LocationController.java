package com.alveshenrique.geoalarm.controller;

import com.alveshenrique.geoalarm.exception.ResourceNotFoundException;
import com.alveshenrique.geoalarm.model.Location;
import com.alveshenrique.geoalarm.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Allow  Cross-Origin Resource Sharing (CORS) for the frontend app on localhost.
// TODO CHANGE IT FOR PRODUCTION AND CHECK FOR BETTER OPTIONS WITH SPRING SECURITY
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    // get all Locations

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // create location REST API
    @PostMapping("/locations")
    public Location createLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    // get location by id REST API
    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with id " + id + " not found."));
        return  ResponseEntity.ok(location);
    }
}
