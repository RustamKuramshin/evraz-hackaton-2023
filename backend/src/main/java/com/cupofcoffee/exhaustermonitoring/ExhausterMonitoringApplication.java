package com.cupofcoffee.exhaustermonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@EnableKafka
@SpringBootApplication
public class ExhausterMonitoringApplication {

    @Autowired
    private ExhausterDao exhausterDao;

    public static void main(String[] args) {
        SpringApplication.run(ExhausterMonitoringApplication.class, args);
    }

    @PostConstruct
    public void onStartup() {
        exhausterDao.getAllExhausterMetricsForAllMachines((Consumer<Object>) System.out::println);
    }
}
