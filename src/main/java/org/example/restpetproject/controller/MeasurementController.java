package org.example.restpetproject.controller;

import jakarta.validation.Valid;
import org.example.restpetproject.dto.MeasurementGetResponse;
import org.example.restpetproject.dto.MeasurementSaveRequest;
import org.example.restpetproject.service.MeasurementService;
import org.example.restpetproject.util.ErrorsUtil;
import org.example.restpetproject.exception.MeasurementSaveException;
import org.example.restpetproject.exception.SensorNotFoundException;
import org.example.restpetproject.validator.MeasurementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementValidator measurementValidator;
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementValidator measurementValidator, MeasurementService measurementService) {
        this.measurementValidator = measurementValidator;
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveMeasurement(@RequestBody @Valid MeasurementSaveRequest measurementSaveRequest,
                                                      BindingResult bindingResult) {
        measurementValidator.validate(measurementSaveRequest, bindingResult);
        if (bindingResult.hasErrors()) throw new MeasurementSaveException(ErrorsUtil.errorsJoin(bindingResult));

        measurementService.save(measurementSaveRequest);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementGetResponse> getMeasurements() {
        return measurementService.getMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.getMeasurements().stream().filter(m -> m.isRaining()).count();
    }

    @ExceptionHandler(MeasurementSaveException.class)
    public ProblemDetail handleMeasurementException(MeasurementSaveException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(SensorNotFoundException.class)
    public ProblemDetail handleSensorNotFoundException(SensorNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
