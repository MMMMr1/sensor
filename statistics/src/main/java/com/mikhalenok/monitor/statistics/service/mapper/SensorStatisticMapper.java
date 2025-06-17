package com.mikhalenok.monitor.statistics.service.mapper;

import com.mikhalenok.monitor.statistics.data.SensorStatistic;
import com.mikhalenok.monitor.statistics.presentation.model.SensorStatisticRs;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SensorStatisticMapper {

    SensorStatisticRs toSensorStatisticRs(SensorStatistic sensorStatistic);
}
