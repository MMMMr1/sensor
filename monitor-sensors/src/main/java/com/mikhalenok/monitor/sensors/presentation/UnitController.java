package com.mikhalenok.monitor.sensors.presentation;

import com.mikhalenok.monitor.sensors.infrastructure.aspect.Log;
import com.mikhalenok.monitor.sensors.presentation.model.unit.UnitRs;
import com.mikhalenok.monitor.sensors.presentation.model.unit.UnitRq;
import com.mikhalenok.monitor.sensors.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/units")
@Tag(name = "Units")
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    @Operation(summary = "Get all units", description = "Returns a list of all available units.")
    public List<UnitRs> getUnits() {
        return unitService.getAllUnits();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get unit by ID", description = "Fetches a specific unit based on its ID.")
    public UnitRs getUnit(@PathVariable Long id) {
        return unitService.getUnit(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new unit", description = "Adds a new unit to the system.")
    public UnitRs saveUnit(@RequestBody UnitRq unitRq) {
        return unitService.saveUnit(unitRq);
    }

    @PatchMapping("/{id}/name")
    @Operation(summary = "Update a unit", description = "Updates a unit.")
    public UnitRs updateUnitName(@PathVariable Long id, @RequestParam String newName) {
        return unitService.updateUnitName(id, newName);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete unit", description = "Removes a unit from the system by its ID.")
    public void deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
    }
}