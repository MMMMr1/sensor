package com.mikhalenok.monitor.sensors.data.repository;

import com.mikhalenok.monitor.sensors.data.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
