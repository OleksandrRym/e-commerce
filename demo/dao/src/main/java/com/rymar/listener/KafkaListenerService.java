package com.rymar.listener;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaListenerService {

  @KafkaListener(
      topics = "orymar-events",
      groupId = "orymar-group",
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(Map<String, Object> message) {
    log.info("Received message in Listener: {}", message);

    // Приклад обробки даних
    String name = (String) message.get("name");
    Integer age = (Integer) message.get("age");

    log.info("Extracted data: Name = {}, Age = {}", name, age);
  }
}
