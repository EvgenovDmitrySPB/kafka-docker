package com.layo.kafkaexample.engine;

import com.layo.kafkaexample.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private KafkaTemplate<String, Message> kafkaTemplate;

    void sendMessage(Message message, String topicName) {
        kafkaTemplate.send(topicName, message);
    }
}
