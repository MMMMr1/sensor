package com.mikhalenok.monitor.sensors.presentation.model.sensor;


import com.fasterxml.jackson.annotation.JsonView;
import com.mikhalenok.monitor.sensors.presentation.model.view.Views;

public record RangeRs(
        @JsonView(Views.Public.class) int from,
        @JsonView(Views.Public.class) int to) {
}
