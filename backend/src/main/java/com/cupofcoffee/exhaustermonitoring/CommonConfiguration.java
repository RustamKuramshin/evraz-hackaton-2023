package com.cupofcoffee.exhaustermonitoring;

import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Detail;
import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Exhauster;
import com.cupofcoffee.exhaustermonitoring.db.pg.entities.Notification;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.concurrent.Executor;

@Configuration
public class CommonConfiguration {

    @Value("${influx-db.host}")
    private String influxDbHost;

    @Value("${influx-db.port}")
    private String influxDbPort;

    @Value("${influx-db.user}")
    private String influxDbUser;

    @Value("${influx-db.password}")
    private String influxDbPassword;

    @Value("${influx-db.dbname}")
    private String influxDbDbname;

    @Value("${influx-db.policy}")
    private String influxDbPolicy;

    @Value("${core.pool.size}")
    private int corePoolSize;

    @Value("${max.pool.size}")
    private int maxPoolSize;

    @Value("${queue.capacity}")
    private int queueCapacity;

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
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurer() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig, CorsRegistry cors) {

                restConfig.exposeIdsFor(Detail.class);
                restConfig.exposeIdsFor(Exhauster.class);
                restConfig.exposeIdsFor(Notification.class);
            }
        };
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
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("AsyncMethods-");
        executor.initialize();
        return executor;
    }

    @Bean
    public InfluxDB influxClient() {

        String influxDBUrl = String.format("http://%s:%s",influxDbHost, influxDbPort);

        InfluxDB influxDB = InfluxDBFactory.connect(influxDBUrl, influxDbUser, influxDbPassword);

        influxDB.createDatabase(influxDbDbname);
        influxDB.createRetentionPolicy(
            influxDbPolicy, influxDbDbname, "30d", 1, true);

        influxDB.setRetentionPolicy(influxDbPolicy);
        influxDB.setDatabase(influxDbDbname);

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);

        return influxDB;
    }

    @Bean
    public MessageListenerContainer getMessageListenerContainer(MongoTemplate mongoTemplate) {
        return new DefaultMessageListenerContainer(mongoTemplate);
    }
}
