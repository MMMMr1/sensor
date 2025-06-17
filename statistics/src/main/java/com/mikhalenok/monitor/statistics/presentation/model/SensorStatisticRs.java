package com.mikhalenok.monitor.statistics.presentation.model;


import java.time.LocalDate;
import java.util.Map;

public record SensorStatisticRs(
        LocalDate date,
        int totalSensors,
        Map<String, Integer> sensorTypesCount) {
}
