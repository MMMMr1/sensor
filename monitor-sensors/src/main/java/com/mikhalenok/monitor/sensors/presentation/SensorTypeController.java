package com.mikhalenok.monitor.sensors.presentation;

import com.mikhalenok.monitor.sensors.infrastructure.aspect.Log;
import com.mikhalenok.monitor.sensors.presentation.model.sensortype.SensorTypeRs;
import com.mikhalenok.monitor.sensors.presentation.model.sensortype.SensorTypeRq;
import com.mikhalenok.monitor.sensors.service.SensorTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/types")
@Tag(name = "Sensor Types", description = "Manage sensor types")
public class SensorTypeController {

    private final SensorTypeService sensorTypeService;

    @GetMapping
    @Operation(summary = "Get all sensor types", description = "Returns a list of all available sensor types.")
    public List<SensorTypeRs> getTypes() {
        return sensorTypeService.getAllTypes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sensor type by ID", description = "Fetches a specific sensor type based on its ID.")
    public SensorTypeRs getType(@PathVariable Long id) {
        return sensorTypeService.getType(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new sensor type", description = "Adds a new sensor type to the system.")
    public SensorTypeRs saveType(@RequestBody SensorTypeRq sensorTypeRq) {
        return sensorTypeService.saveType(sensorTypeRq);
    }

    @PatchMapping("/{id}/name")
    @Operation(summary = "Update sensor type name", description = "Updates the name of an existing sensor type.")
    public SensorTypeRs updateTypeName(@PathVariable Long id, @RequestParam String newName) {
        return sensorTypeService.updateTypeName(id, newName);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sensor type", description = "Removes a sensor type from the system by its ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteType(@PathVariable Long id) {
        sensorTypeService.deleteType(id);
    }
}