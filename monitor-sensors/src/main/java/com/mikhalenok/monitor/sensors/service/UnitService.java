package com.mikhalenok.monitor.sensors.service;

import com.mikhalenok.monitor.sensors.presentation.model.unit.UnitRs;
import com.mikhalenok.monitor.sensors.presentation.model.unit.UnitRq;
import com.mikhalenok.monitor.sensors.infrastructure.exception.NotFoundException;
import com.mikhalenok.monitor.sensors.service.mapper.UnitMapper;
import com.mikhalenok.monitor.sensors.data.Unit;
import com.mikhalenok.monitor.sensors.data.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnitService {
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    public List<UnitRs> getAllUnits() {
        return unitRepository.findAll()
                .stream()
                .map(unitMapper::toUnitDto)
                .toList();
    }

    public UnitRs getUnit(Long id) {
        return unitRepository.findById(id)
                .map(unitMapper::toUnitDto)
                .orElseThrow(throwNotFoundException(id));
    }

    public UnitRs updateUnitName(Long id, String newName) {
        return unitRepository.findById(id)
                .map(unit -> {
                    unit.setName(newName);
                    return unit;
                })
                .map(unitRepository::save)
                .map(unitMapper::toUnitDto)
                .orElseThrow(throwNotFoundException(id));
    }

    public void deleteUnit(Long id) {
        if (!unitRepository.existsById(id)) {
            throw new NotFoundException("Unit with id %s does not exist.".formatted(id));
        }
        unitRepository.deleteById(id);
    }

    public UnitRs saveUnit(UnitRq unitRq) {
        Unit unit = unitMapper.toUnit(unitRq);
        Unit savedUnit = unitRepository.save(unit);
        return unitMapper.toUnitDto(savedUnit);
    }

    private Supplier<NotFoundException> throwNotFoundException(Long id) {
        return () -> new NotFoundException("Unit with id %s does not exist.".formatted(id));
    }
}
