package com.mikhalenok.monitor.sensors.data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
@Table(name = "sensor_statistics")
public class SensorStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int totalSensors;

    @ElementCollection
    private Map<String, Integer> sensorTypesCount;
}