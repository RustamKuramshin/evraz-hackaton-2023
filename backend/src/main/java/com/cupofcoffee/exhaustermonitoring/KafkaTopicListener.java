package com.cupofcoffee.exhaustermonitoring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaTopicListener {

  @KafkaListener(
    topics = "${kafka-topic}",
    groupId = "${kafka.group-id}",
    topicPartitions = @TopicPartition(topic = "${kafka-topic}", partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")})
  )
  public void handle(String message) {
      log.info("got msg: " + message);
  }

}
