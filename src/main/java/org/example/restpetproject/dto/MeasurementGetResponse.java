package org.example.restpetproject.dto;

public record MeasurementGetResponse (
        Double temperature,
        Boolean isRaining,
        String sensorName
) {
}
