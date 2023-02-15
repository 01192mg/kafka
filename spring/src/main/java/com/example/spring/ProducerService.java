package com.example.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProducerService {
    private static final String TOPIC_NAME = "topic5";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MyMessage> newKafkaTemplate;

    public void sendJson(MyMessage message) {
        newKafkaTemplate.send(TOPIC_NAME, message);
    }

    public void send(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendWithCallback(String message) {
        kafkaTemplate
                .send(TOPIC_NAME, message)
                .handle((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Sent " + message + ", offset:" + result.getRecordMetadata().offset());
                    } else {
                        System.out.println("Failed " + message + " due to : " + ex.getMessage());
                    }
                    return null;
                });
    }
}
