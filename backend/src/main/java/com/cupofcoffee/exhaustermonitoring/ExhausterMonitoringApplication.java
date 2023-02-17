package com.cupofcoffee.exhaustermonitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@EnableKafka
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class ExhausterMonitoringApplication {

    private final ExhausterDao exhausterDao;

    public static void main(String[] args) {
        SpringApplication.run(ExhausterMonitoringApplication.class, args);
    }

    @PostConstruct
    public void onStartup() {
        log.info("MONGODB: Start read change stream");
        exhausterDao.getAllExhausterMetricsForAllMachines((Consumer<Object>) System.out::println);
    }
}
