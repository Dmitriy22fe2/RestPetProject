package org.example.restpetproject.validators;

import org.example.restpetproject.dto.MeasurementSaveRequest;
import org.example.restpetproject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementSaveRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementSaveRequest measurementSaveRequest = (MeasurementSaveRequest) target;

        if (sensorService.getSensorBySensorName(measurementSaveRequest.sensorName()).isEmpty()) {
            errors.rejectValue("sensorName", "", "sensor with this name is not registered");
        }
    }
}
