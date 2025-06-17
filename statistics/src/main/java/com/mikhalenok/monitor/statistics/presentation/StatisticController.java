package com.mikhalenok.monitor.statistics.presentation;

import com.mikhalenok.monitor.statistics.infrastructure.aspect.Log;
import com.mikhalenok.monitor.statistics.presentation.model.SensorStatisticRs;
import com.mikhalenok.monitor.statistics.service.SensorStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticController {
    private final SensorStatisticService sensorStatisticService;


    @GetMapping
    public List<SensorStatisticRs> getStatistics(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return sensorStatisticService.getStatistics(start, end);
    }
}
