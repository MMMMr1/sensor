package com.mikhalenok.monitor.sensors.presentation.model.sensor;

import com.mikhalenok.monitor.sensors.presentation.validator.ValidRange;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record SensorRq(
        @NotBlank
        @Size(min = 3, max = 30)
        @Schema(description = "Sensor name", example = "Barometer")
        String name,
        @NotBlank
        @Size(max = 15)
        @Schema(description = "Sensor model", example = "ac-23")
        String model,
        @ValidRange
        @Schema(description = "Defines range limits for the sensor", example = "{ 'from': 10, 'to': 100 }")
        RangeRq range,
        @NotNull
        @Schema(description = "Sensor type ID", example = "1")
        Long type,
        @Schema(description = "Optional sensor unit ID", example = "5")
        Long unit,
        @Size(max = 40)
        @Schema(description = "Sensor location details", example = "kitchen")
        String location,
        @Size(max = 200)
        @Schema(description = "Sensor description", example = "High-precision temperature sensor with advanced calibration.")
        String description
) {

}
