package com.mikhalenok.monitor.sensors.service.mapper;

import com.mikhalenok.monitor.sensors.presentation.model.sensortype.SensorTypeRs;
import com.mikhalenok.monitor.sensors.presentation.model.sensortype.SensorTypeRq;
import com.mikhalenok.monitor.sensors.data.SensorType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SensorTypeMapper {
    SensorTypeRs toSensorTypeRs(SensorType sensorType);
    SensorType toSensorType(SensorTypeRq sensorTypeRq);
}
