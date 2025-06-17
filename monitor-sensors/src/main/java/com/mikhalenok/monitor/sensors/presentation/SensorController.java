package com.mikhalenok.monitor.sensors.presentation;

import com.fasterxml.jackson.annotation.JsonView;
import com.mikhalenok.monitor.sensors.infrastructure.aspect.Log;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorRq;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorRs;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorSearchRq;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorSearchRs;
import com.mikhalenok.monitor.sensors.presentation.model.view.Views;
import com.mikhalenok.monitor.sensors.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
@Tag(name = "Sensors", description = "Manage sensors")
public class SensorController {
    private final SensorService sensorService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VIEWER')")
    @JsonView(Views.Public.class)
    @Operation(summary = "Get all sensors", description = "Returns a list of sensors")
    public List<SensorRs> getSensors() {
        return sensorService.getSensors();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(Views.Admin.class)
    @Operation(summary = "Get sensors for admin", description = "Returns sensors with admin privileges")
    public List<SensorRs> getSensorsForAdmin() {
        return sensorService.getSensors();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'VIEWER')")
    @JsonView(Views.Public.class)
    @Operation(summary = "Search sensors", description = "Filter sensors by name, model and pagination")
    public List<SensorSearchRs> searchSensors(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "50") int limit,
                                              @RequestBody @Valid SensorSearchRq sensorSearchRq) {
        return sensorService.searchSensors(page, limit, sensorSearchRq);
    }
    @GetMapping("/admin/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(Views.Admin.class)
    @Operation(summary = "Search sensors", description = "Filter sensors by name, model and pagination")
    public List<SensorSearchRs> searchSensorsForAdmin(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "50") int limit,
                                              @RequestBody @Valid SensorSearchRq sensorSearchRq) {
        return sensorService.searchSensors(page, limit, sensorSearchRq);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get a sensor by ID", description = "Returns a sensor based on its ID")
    public SensorRs getSensor(@PathVariable Long id) {
        return sensorService.getSensor(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new sensor", description = "Saves a new sensor and returns its ID")
    public Long saveSensor(@RequestBody @Valid SensorRq sensor) {
        return sensorService.saveSensor(sensor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update sensor", description = "Updates an existing sensor based on its ID")
    public SensorRs updateSensor(@PathVariable Long id, @RequestBody @Valid SensorRq sensor) {
        return sensorService.updateSensor(id, sensor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete sensor", description = "Deletes a sensor based on its ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUnit(@PathVariable Long id) {
        sensorService.deleteSensor(id);
    }
}
