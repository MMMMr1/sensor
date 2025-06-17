package com.mikhalenok.monitor.sensors.data.repository.spec;

import com.mikhalenok.monitor.sensors.presentation.model.sensor.SensorSearchRq;
import com.mikhalenok.monitor.sensors.data.Sensor;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class SensorSpecification {
    public Specification<Sensor> searchSensor(SensorSearchRq sensorSearchRq) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (StringUtils.isNotBlank(sensorSearchRq.name())) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + sensorSearchRq.name().toLowerCase() + "%"));

            }
            if (StringUtils.isNotBlank(sensorSearchRq.model())) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("model")), "%" + sensorSearchRq.model().toLowerCase() + "%"));

            }
            query.distinct(true);
            return predicate;
        };
    }
}
