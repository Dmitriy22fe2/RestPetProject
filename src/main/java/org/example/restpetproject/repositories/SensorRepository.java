package org.example.restpetproject.repositories;

import org.example.restpetproject.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> getSensorBySensorName(String sensorName);
}
