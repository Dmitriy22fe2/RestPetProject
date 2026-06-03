package org.example.restpetproject.mappers;

import org.example.restpetproject.dto.MeasurementGetResponse;
import org.example.restpetproject.dto.MeasurementSaveRequest;
import org.example.restpetproject.models.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "raining", target = "isRaining")
    @Mapping(source = "sensor.sensorName", target = "sensorName")
    MeasurementGetResponse toResponse(Measurement measurement);

    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "isRaining", target = "raining")
    @Mapping(target = "sensor", ignore = true)
    Measurement toEntity(MeasurementSaveRequest measurementSaveRequest);
}
