package com.mikhalenok.monitor.statistics.infrastructure;

import com.mikhalenok.monitor.statistics.service.SensorStatisticJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {


    @Value("${job.sensorStatisticsJob.trigger.hour}")
    private int hour;
    @Value("${job.sensorStatisticsJob.trigger.minute}")
    private int minute;
    @Bean
    public JobDetail sensorStatisticsJobDetail() {
        return JobBuilder.newJob(SensorStatisticJob.class)
                .withIdentity("sensorStatisticsJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sensorStatisticsTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(sensorStatisticsJobDetail())
                .withIdentity("sensorStatisticsTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hour, minute))
                .build();
    }
}