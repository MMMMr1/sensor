package com.mikhalenok.monitor.sensors.data.repository;

import com.mikhalenok.monitor.sensors.data.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {
}
