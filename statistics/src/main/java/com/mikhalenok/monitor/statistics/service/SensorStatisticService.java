package com.mikhalenok.monitor.statistics.service;

import com.mikhalenok.monitor.statistics.data.SensorStatistic;
import com.mikhalenok.monitor.statistics.data.repository.SensorStatisticRepository;
import com.mikhalenok.monitor.statistics.presentation.model.SensorStatisticRs;
import com.mikhalenok.monitor.statistics.presentation.model.sensor.SensorRs;
import com.mikhalenok.monitor.statistics.service.mapper.SensorStatisticMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorStatisticService {

    private final SensorStatisticRepository sensorStatisticRepository;
    private final SensorStatisticMapper sensorStatisticMapper;

    public List<SensorStatisticRs> getStatistics(LocalDate start, LocalDate end) {
        return sensorStatisticRepository.findByDateBetween(start, end).stream()
                .map(sensorStatisticMapper::toSensorStatisticRs)
                .toList();
    }

    public void saveStatistic(List<SensorRs> sensors, Map<String, Integer> sensorTypesCount) {
            SensorStatistic statistics = new SensorStatistic();
            statistics.setDate(LocalDate.now());
            statistics.setTotalSensors(sensors.size());
            statistics.setSensorTypesCount(sensorTypesCount);
            sensorStatisticRepository.save(statistics);
        }
}
