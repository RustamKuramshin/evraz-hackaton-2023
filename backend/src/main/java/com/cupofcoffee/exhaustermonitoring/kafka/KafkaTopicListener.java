package com.cupofcoffee.exhaustermonitoring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaTopicListener {
  private final MongoTemplate mongoTemplate;
  @Value("${mongo.collection}")
  private String collection;

  @KafkaListener(
    topics = "${kafka-topic}",
    groupId = "${kafka.group-id}"
  )
  public void handle(@Payload String message) {
    log.info("Got kafka msg: ...");
    log.debug(message);

    mongoTemplate.save(message, collection);

  }

}
