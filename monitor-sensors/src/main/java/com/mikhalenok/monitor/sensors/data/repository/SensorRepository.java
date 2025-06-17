package com.mikhalenok.monitor.sensors.data.repository;

import com.mikhalenok.monitor.sensors.data.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {


    Page<Sensor> findAll(Specification<Sensor> spec, Pageable pageable);
}
