package org.example.restpetproject.mapper;


import org.example.restpetproject.dto.SensorRegistrationRequest;
import org.example.restpetproject.model.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    @Mapping(source = "sensorName", target = "sensorName")
    SensorRegistrationRequest toDto(Sensor sensor);

    @Mapping(source = "sensorName", target = "sensorName")
    Sensor toEntity(SensorRegistrationRequest sensorRegistrationRequest);
}
