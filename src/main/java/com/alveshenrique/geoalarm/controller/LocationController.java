package com.alveshenrique.geoalarm.controller;

import com.alveshenrique.geoalarm.model.Location;
import com.alveshenrique.geoalarm.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
