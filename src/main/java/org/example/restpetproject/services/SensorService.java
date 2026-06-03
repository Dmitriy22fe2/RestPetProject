package org.example.restpetproject.services;

import org.example.restpetproject.dto.SensorRegistrationRequest;
import org.example.restpetproject.mappers.SensorMapper;
import org.example.restpetproject.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.sensorMapper = sensorMapper;
    }

    public Optional<SensorRegistrationRequest> getSensorBySensorName(String sensorName) {
        return sensorRepository.getSensorBySensorName(sensorName).map(sensorMapper::toDto);
    }

    @Transactional
    public void saveSensor(SensorRegistrationRequest sensorRegistrationRequest) {
        sensorRepository.save(sensorMapper.toEntity(sensorRegistrationRequest));
    }
}
