package com.mikhalenok.monitor.sensors.service;

import com.mikhalenok.monitor.sensors.data.repository.SensorTypeRepository;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorRq;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorRs;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorSearchRq;
import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorSearchRs;
import com.mikhalenok.monitor.sensors.infrastructure.exception.NotFoundException;
import com.mikhalenok.monitor.sensors.service.mapper.SensorMapper;
import com.mikhalenok.monitor.sensors.data.Sensor;
import com.mikhalenok.monitor.sensors.data.repository.SensorRepository;
import com.mikhalenok.monitor.sensors.data.repository.UnitRepository;
import com.mikhalenok.monitor.sensors.data.repository.spec.SensorSpecification;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final UnitRepository unitRepository;
    private final SensorTypeRepository sensorTypeRepository;
    private final SensorMapper sensorMapper;

    public Long saveSensor(SensorRq sensorRq) {
        validateSensorRq(sensorRq);
        Sensor sensor = sensorMapper.toSensor(sensorRq);
        if (Objects.isNull(sensorRq.unit())) {
            sensor.setUnit(null);
        }
        Sensor saved = sensorRepository.save(sensor);
        return saved.getId();
    }

    public List<SensorRs> getSensors() {
        return sensorRepository.findAll().stream()
                .map(sensorMapper::toSensorRs)
                .toList();
    }

    public SensorRs getSensor(Long id) {
        return sensorRepository.findById(id)
                .map(sensorMapper::toSensorRs)
                .orElseThrow(throwNotFoundException(id));
    }

    public SensorRs updateSensor(Long id, SensorRq sensorRq) {
        validateSensorRq(sensorRq);
        return sensorRepository.findById(id)
                .map(sensor -> {
                    sensor.setName(sensorRq.name());
                    sensor.setModel(sensorRq.model());
                    sensor.setRangeFrom(sensorRq.range().from());
                    sensor.setRangeTo(sensorRq.range().to());
                    sensor.getType().setId(sensorRq.type());
                    if (nonNull(sensorRq.unit())) {
                        sensor.setUnit(unitRepository.findById(sensorRq.unit())
                                .orElseThrow(() -> new NotFoundException("Unit with id %s does not exist.".formatted(sensorRq.unit()))));
                    }
                    sensor.setDescription(sensorRq.description());
                    sensor.setLocation(sensorRq.location());
                    return sensor;
                })
                .map(sensorRepository::save)
                .map(sensorMapper::toSensorRs)
                .orElseThrow(throwNotFoundException(id));

    }

    public void deleteSensor(Long id) {
        if (!sensorRepository.existsById(id)) {
            throw new NotFoundException("Sensor with id %s does not exist.".formatted(id));
        }
        sensorRepository.deleteById(id);
    }

    public List<SensorSearchRs> searchSensors(int page, int limit, SensorSearchRq sensorSearchRq) {
        if (page > 0) {
            page = page - 1;
        }
        Pageable pageable = PageRequest.of(page, limit);
        Specification<Sensor> spec = SensorSpecification.searchSensor(sensorSearchRq);
        return sensorRepository.findAll(spec, pageable)
                .map(sensorMapper::toSensorSearchRs)
                .stream()
                .toList();
    }

    private Supplier<NotFoundException> throwNotFoundException(Long id) {
        return () -> new NotFoundException("Sensor with id %s does not exist.".formatted(id));
    }

    private void validateSensorRq(SensorRq sensorRq) {

        if (!sensorTypeRepository.existsById(sensorRq.type())) {
            throw new ValidationException("Type with id %s does not exist.".formatted(sensorRq.type()));
        }
        if (nonNull(sensorRq.unit())) {
            if (!unitRepository.existsById(sensorRq.unit())) {
                throw new ValidationException("Unit with id %s does not exist.".formatted(sensorRq.unit()));
            }
        }
    }
}
