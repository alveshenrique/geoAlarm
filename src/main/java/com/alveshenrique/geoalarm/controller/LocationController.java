package com.alveshenrique.geoalarm.controller;

import com.alveshenrique.geoalarm.exception.ResourceNotFoundException;
import com.alveshenrique.geoalarm.model.Location;
import com.alveshenrique.geoalarm.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // update location REST API
    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location locationDetails) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with id " + id + " not found."));

        location.setStreet1(locationDetails.getStreet1());
        location.setStreet2(locationDetails.getStreet2());
        location.setCity(locationDetails.getCity());
        location.setStateRegion(locationDetails.getStateRegion());
        location.setCountry(locationDetails.getCountry());
        location.setZipCode(locationDetails.getZipCode());
        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());

        Location updatedLocation = locationRepository.save(location);

        return ResponseEntity.ok(updatedLocation);
    }

    // delete location REST API
    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLocation(@PathVariable Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with id " + id + " not found."));
        locationRepository.delete(location);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
