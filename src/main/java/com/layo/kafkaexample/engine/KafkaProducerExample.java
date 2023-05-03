package com.layo.kafkaexample.engine;

import com.layo.kafkaexample.model.Message;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducerExample {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private KafkaTemplate<String, String> kafkaTemplate;
    private RoutingKafkaTemplate routingTemplate;
    private KafkaTemplate<String, Message> messageKafkaTemplate;

    public KafkaProducerExample(KafkaTemplate<String, String> kafkaTemplate,
                                RoutingKafkaTemplate routingTemplate,
                                KafkaTemplate<String, Message> messageKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.routingTemplate = routingTemplate;
        this.messageKafkaTemplate = messageKafkaTemplate;
    }

    public void sendMessageTemplate(String message, String topic) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }

    public void sendMessageObjectMessage(Message message, String topic) {
        ListenableFuture<SendResult<String, Message>> future = messageKafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onSuccess(SendResult<String, Message> result) {
                logger.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }

    public void sendMessageRouting(String message, String topic) {
        routingTemplate.send(topic, message.getBytes());
    }
}
