package com.mikhalenok.monitor.sensors.presentation.model.sensortype;

import jakarta.validation.constraints.NotNull;


public record SensorTypeRq(@NotNull String name) {
}