package com.mikhalenok.monitor.statistics.data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
@Table(name = "sensor_statistics")
public class SensorStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int totalSensors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "sensor_types_count",
            joinColumns = @JoinColumn(name = "statistics_id"))
    @MapKeyColumn(name = "sensor_type")
    @Column(name = "count")
    private Map<String, Integer> sensorTypesCount;
}