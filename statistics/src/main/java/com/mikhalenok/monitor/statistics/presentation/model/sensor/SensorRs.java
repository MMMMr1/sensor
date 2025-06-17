package com.mikhalenok.monitor.statistics.presentation.model.sensor;


public record SensorRs (
    Long id,
    String name,
    String model,
    RangeRs range,
    String type,
    String unit,
    String location,
    String description)
{
}
