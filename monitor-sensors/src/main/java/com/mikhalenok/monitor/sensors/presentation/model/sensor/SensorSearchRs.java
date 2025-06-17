package com.mikhalenok.monitor.sensors.presentation.model.sensor;

import com.fasterxml.jackson.annotation.JsonView;
import com.mikhalenok.monitor.sensors.presentation.model.view.Views;

public record SensorSearchRs(

        @JsonView(Views.Admin.class)
        Long id,
        @JsonView(Views.Public.class)
        String name,
        @JsonView(Views.Public.class)
        String model,
        @JsonView(Views.Public.class)
        RangeSearchRs range,
        @JsonView(Views.Public.class)
        String type,
        @JsonView(Views.Public.class)
        String unit,
        @JsonView(Views.Public.class)
        String location,
        @JsonView(Views.Public.class)
        String description) {

}
