package org.example.restpetproject.validator;

import org.example.restpetproject.dto.SensorRegistrationRequest;
import org.example.restpetproject.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorRegistrationRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorRegistrationRequest sensor = (SensorRegistrationRequest) target;

        if (sensorService.getSensorBySensorName(sensor.sensorName()).isPresent()) {
            errors.rejectValue("sensorName", "", "This sensor name already exists");
        }
    }
}
