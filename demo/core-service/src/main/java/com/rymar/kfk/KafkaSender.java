package com.rymar.kfk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object message) {
        log.info("Sending message to Kafka: {}", message);

        kafkaTemplate.send(topic, message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
                    } else {
                        log.error("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                    }
                });
    }
}
