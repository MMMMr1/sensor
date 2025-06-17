package com.mikhalenok.monitor.sensors.service;

import com.mikhalenok.monitor.sensors.presentation.model.sensortype.SensorTypeRs;
import com.mikhalenok.monitor.sensors.presentation.model.sensortype.SensorTypeRq;
import com.mikhalenok.monitor.sensors.infrastructure.exception.NotFoundException;
import com.mikhalenok.monitor.sensors.service.mapper.SensorTypeMapper;
import com.mikhalenok.monitor.sensors.data.SensorType;
import com.mikhalenok.monitor.sensors.data.repository.SensorTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorTypeService {
    private final SensorTypeRepository sensorTypeRepository;
    private final SensorTypeMapper sensorTypeMapper;

    public List<SensorTypeRs> getAllTypes() {
        return sensorTypeRepository.findAll()
                .stream()
                .map(sensorTypeMapper::toSensorTypeRs)
                .toList();
    }

    public SensorTypeRs getType(Long id) {
        return sensorTypeRepository.findById(id)
                .map(sensorTypeMapper::toSensorTypeRs)
                .orElseThrow(throwNotFoundException(id));
    }

    public SensorTypeRs updateTypeName(Long id, String newName) {
        return sensorTypeRepository.findById(id)
                .map(sensorType -> {
                    sensorType.setName(newName);
                    return sensorType;
                })
                .map(sensorTypeRepository::save)
                .map(sensorTypeMapper::toSensorTypeRs)
                .orElseThrow(throwNotFoundException(id));

    }

    public void deleteType(Long id) {
        if (!sensorTypeRepository.existsById(id)) {
            throw new NotFoundException("Unit with id %s does not exist.".formatted(id));
        }
        sensorTypeRepository.deleteById(id);
    }

    public SensorTypeRs saveType(SensorTypeRq sensorTypeRq) {
        SensorType type = sensorTypeMapper.toSensorType(sensorTypeRq);
        SensorType savedSensorType = sensorTypeRepository.save(type);
        return sensorTypeMapper.toSensorTypeRs(savedSensorType);
    }

    private Supplier<NotFoundException> throwNotFoundException(Long id) {
        return () -> new NotFoundException("Unit with id %s does not exist.".formatted(id));
    }
}
