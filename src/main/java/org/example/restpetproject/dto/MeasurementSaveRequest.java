package org.example.restpetproject.dto;

import jakarta.validation.constraints.*;

public record MeasurementSaveRequest(
        @NotNull(message = "temperature should be entered")
        @Min(value = -100, message = "temperature should not be less then -100 degrees")
        @Max(value = 100, message = "temperature should not be more then 100 degrees")
        Double temperature,

        @NotNull(message = "should be entered is it raining at this moment")
        Boolean isRaining,

        @NotEmpty(message = "sensor name shoud be entered")
        @Size(min = 3, max = 30, message = "sensor name should be not less then 3 letters and no more then 30 letters")
        String sensorName
) {
}
