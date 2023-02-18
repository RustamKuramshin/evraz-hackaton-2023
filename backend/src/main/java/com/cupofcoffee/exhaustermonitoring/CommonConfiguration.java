package com.cupofcoffee.exhaustermonitoring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Pong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;

@Configuration
public class CommonConfiguration {

    @Value("${influx-db.url}")
    private String influxDbUrl;

    @Value("${influx-db.user}")
    private String influxDbUser;

    @Value("${influx-db.password}")
    private String influxDbPassword;

    @Value("${influx-db.dbname}")
    private String influxDbDbname;

    @Value("${influx-db.policy}")
    private String influxDbPolicy;

    @Bean
    public Module getJdk8Module() {
        return new Jdk8Module();
    }

    @Bean
    public Module getParanamerModule() {
        return new ParanamerModule();
    }

    @Bean
    public Module getJavaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("AsyncMethods-");
        executor.initialize();
        return executor;
    }

    @Bean
    public InfluxDB influxClient() {
        InfluxDB influxDB = InfluxDBFactory.connect(influxDbUrl, influxDbUser, influxDbPassword);

        influxDB.createDatabase(influxDbDbname);
        influxDB.createRetentionPolicy(
                influxDbPolicy, influxDbDbname, "30d", 1, true);

        influxDB.setRetentionPolicy(influxDbPolicy);
        influxDB.setDatabase(influxDbDbname);

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);

        return influxDB;
    }

    public MessageListenerContainer getMessageListenerContainer(MongoTemplate mongoTemplate) {
        return new DefaultMessageListenerContainer(mongoTemplate);
    }
}
