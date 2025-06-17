package com.mikhalenok.monitor.sensors.service.mapper;

import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorRq;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorRs;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorSearchRs;
import com.mikhalenok.monitor.sensors.data.Sensor;
import com.mikhalenok.monitor.sensors.data.SensorType;
import com.mikhalenok.monitor.sensors.data.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SensorMapper {
    @Mapping(target = "type", source = "sensor.type", qualifiedByName = "mapType")
    @Mapping(target = "unit", source = "sensor.unit", qualifiedByName = "mapUnit")
    @Mapping(target = "range.from", source = "sensor.rangeFrom")
    @Mapping(target = "range.to", source = "sensor.rangeTo")
    SensorRs toSensorRs(Sensor sensor);

    @Mapping(target = "type.id", source = "sensorRq.type")
    @Mapping(target = "unit.id", source = "sensorRq.unit", qualifiedByName = "mapUnitId")
    @Mapping(target = "rangeFrom", source = "sensorRq.range.from")
    @Mapping(target = "rangeTo", source = "sensorRq.range.to")
    Sensor toSensor(SensorRq sensorRq);

    @Mapping(target = "type", source = "sensor.type", qualifiedByName = "mapType")
    @Mapping(target = "unit", source = "sensor.unit", qualifiedByName = "mapUnit")
    @Mapping(target = "range.from", source = "sensor.rangeFrom")
    @Mapping(target = "range.to", source = "sensor.rangeTo")
    SensorSearchRs toSensorSearchRs(Sensor sensor);

    @Named("mapType")
    default String mapType(SensorType sensorType) {
        return isNull(sensorType) ? null : sensorType.getName();
    }

    @Named("mapUnit")
    default String mapUnit(Unit unit) {
        return isNull(unit) ? null : unit.getName();
    }

    @Named("mapUnitId")
    default Long mapUnitId(Long unit) {
        return isNull(unit) ? null : unit;
    }
}
