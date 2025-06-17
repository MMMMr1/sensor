package com.mikhalenok.monitor.sensors.service.mapper;

import com.mikhalenok.monitor.sensors.presentation.model.unit.UnitRq;
import com.mikhalenok.monitor.sensors.presentation.model.unit.UnitRs;
import com.mikhalenok.monitor.sensors.data.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnitMapper {
    UnitRs toUnitDto(Unit unit);
    Unit toUnit(UnitRq unit);
}
