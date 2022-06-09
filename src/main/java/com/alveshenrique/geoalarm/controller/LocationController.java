package com.alveshenrique.geoalarm.controller;

import com.alveshenrique.geoalarm.model.Location;
import com.alveshenrique.geoalarm.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
