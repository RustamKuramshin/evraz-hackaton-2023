package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class ExhausterMonitoringApplication {

    private final ExhausterDaoImpl exhausterDao;

    public static void main(String[] args) {
        SpringApplication.run(ExhausterMonitoringApplication.class, args);
    }
}
