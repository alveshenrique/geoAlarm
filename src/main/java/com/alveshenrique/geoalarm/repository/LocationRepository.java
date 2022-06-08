package com.alveshenrique.geoalarm.repository;

import com.alveshenrique.geoalarm.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository <Location, Long> {
}
