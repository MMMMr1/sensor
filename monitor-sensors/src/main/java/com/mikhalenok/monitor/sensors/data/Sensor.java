package com.mikhalenok.monitor.sensors.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "sensors")
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false)
    private String model;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private SensorType type;

    @Positive
    private int rangeFrom;

    @Positive
    private int rangeTo;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Size(max = 40)
    private String location;

    @Size(max = 200)
    private String description;
}
