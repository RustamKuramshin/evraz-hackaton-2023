package com.cupofcoffee.exhaustermonitoring.kafka;

import com.cupofcoffee.exhaustermonitoring.MetricsTimeSeriesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaTopicListener {
  private final ObjectMapper objectMapper;
  private final MongoTemplate mongoTemplate;
  @Value("${mongo.collection}")
  private String collection;

  private final MetricsTimeSeriesService metricsTimeSeriesService;

  @KafkaListener(
    topics = "${kafka-topic}",
    groupId = "${kafka.group-id}"
  )
  public void handle(ConsumerRecord<?, ?> record) {
    String message = record.value().toString();

    try {
      metricsTimeSeriesService.saveToInfluxDb(message);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    log.info("Got kafka msg: ...");
    try {
      log.debug(message);

      HashMap<Object, Object> dataMap = objectMapper.readValue(message, HashMap.class);
      dataMap.put("kafkaTimestamp", new Date(record.timestamp()));

      mongoTemplate.save(objectMapper.writeValueAsString(dataMap), collection);
    } catch (Exception e) {
      log.error("Error while processing kafka event", e);
    }

  }

}
