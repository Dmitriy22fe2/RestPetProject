package org.example.restpetproject.service;

import org.example.restpetproject.dto.MeasurementGetResponse;
import org.example.restpetproject.dto.MeasurementSaveRequest;
import org.example.restpetproject.mapper.MeasurementMapper;
import org.example.restpetproject.model.Measurement;
import org.example.restpetproject.model.Sensor;
import org.example.restpetproject.repository.MeasurementRepository;
import org.example.restpetproject.repository.SensorRepository;
import org.example.restpetproject.exception.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final MeasurementMapper measurementMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository, MeasurementMapper measurementMapper) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
        this.measurementMapper = measurementMapper;
    }

    @Transactional
    public void save(MeasurementSaveRequest measurementSaveRequest) {
        Measurement measurement = measurementMapper.toEntity(measurementSaveRequest);
        Sensor sensor = sensorRepository.getSensorBySensorName(measurementSaveRequest.sensorName())
                .orElseThrow(() -> new SensorNotFoundException("sensor not found"));

        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }

    public List<MeasurementGetResponse> getMeasurements() {
        return measurementRepository.findAll().stream().map(measurementMapper::toResponse).collect(Collectors.toList());
    }
}
