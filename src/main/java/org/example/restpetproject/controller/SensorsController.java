package org.example.restpetproject.controller;

import jakarta.validation.Valid;
import org.example.restpetproject.dto.SensorRegistrationRequest;
import org.example.restpetproject.service.SensorService;
import org.example.restpetproject.util.ErrorsUtil;
import org.example.restpetproject.exception.SensorRegistrationException;
import org.example.restpetproject.validator.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorValidator sensorValidator;
    private final SensorService sensorService;

    @Autowired
    public SensorsController(SensorValidator sensorValidator, SensorService sensorService) {
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorRegistrationRequest sensorRegistrationRequest,
                                                     BindingResult bindingResult) {

        sensorValidator.validate(sensorRegistrationRequest, bindingResult);

        if (bindingResult.hasErrors()) throw new SensorRegistrationException(ErrorsUtil.errorsJoin(bindingResult));

        sensorService.saveSensor(sensorRegistrationRequest);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(SensorRegistrationException.class)
    public ProblemDetail handleNameRegistered(SensorRegistrationException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
