package com.mikhalenok.monitor.sensors.data.repository;

import com.mikhalenok.monitor.sensors.data.SensorStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SensorStatisticsRepository extends JpaRepository<SensorStatistics, Long> {
    List<SensorStatistics> findByDateBetween(LocalDate start, LocalDate end);
}