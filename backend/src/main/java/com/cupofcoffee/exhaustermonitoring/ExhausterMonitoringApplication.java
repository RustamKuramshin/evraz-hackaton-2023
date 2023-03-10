package com.cupofcoffee.exhaustermonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableKafka
@SpringBootApplication
public class ExhausterMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExhausterMonitoringApplication.class, args);
    }
}
