package com.mikhalenok.monitor.statistics.service;

import com.mikhalenok.monitor.statistics.presentation.model.auth.LoginRq;
import com.mikhalenok.monitor.statistics.presentation.model.sensor.SensorRs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorStatisticJob implements Job {
    @Value("${url.login}")
    private String loginUrl;
    @Value("${url.sensors}")
    private String sensorsUrl;
    @Value("${job.sensorStatisticsJob.login}")
    private String login;
    @Value("${job.sensorStatisticsJob.password}")
    private String password;

    private final RestClient restClient;
    private final SensorStatisticService sensorStatisticService;

    @Override
    public void execute(JobExecutionContext context) {
        String adminToken = getToken();
        List<SensorRs> sensors = loadSensorRs(adminToken);

        Map<String, Integer> sensorTypesCount = sensors.stream()
                .collect(Collectors.groupingBy(SensorRs::type, Collectors.summingInt(s -> 1)));

        sensorStatisticService.saveStatistic(sensors, sensorTypesCount);
        log.info("Statistics updated at: {}", LocalDate.now());
    }

    private List<SensorRs> loadSensorRs(String adminToken) {
        return restClient.get()
                .uri(sensorsUrl)
                .headers(headers -> headers.setBearerAuth(adminToken))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    private String getToken() {
        return restClient.post()
                .uri(loginUrl)
                .contentType(APPLICATION_JSON)
                .body(new LoginRq(login, password))
                .retrieve()
                .body(String.class);
    }
}
