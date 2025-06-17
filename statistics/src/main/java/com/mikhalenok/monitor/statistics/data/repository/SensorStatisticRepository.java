package com.mikhalenok.monitor.statistics.data.repository;

import com.mikhalenok.monitor.statistics.data.SensorStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SensorStatisticRepository extends JpaRepository<SensorStatistic, Long> {
    List<SensorStatistic> findByDateBetween(LocalDate start, LocalDate end);
}