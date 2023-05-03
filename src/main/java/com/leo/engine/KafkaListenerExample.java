package com.leo.engine;

import com.leo.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerExample {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = "topic-1")
    void listener1(String data) {
        LOG.info("Topic 1: Got message " + data);
    }

    @KafkaListener(topics = "topic-2")
    void listener2(String data) {
        LOG.info("Topic 2: Got message " + data);
    }

//    @KafkaListener(
//            topics = "topic-1, topic-2",
//            groupId = "topic-group")
//    void commonListenerForMultipleTopics1(String message) {
//        LOG.info("MultipleTopicListener - {}", message);
//    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "topic-3", partitionOffsets = {
            @PartitionOffset(partition = "0", initialOffset = "0") }), groupId = "topic-group-3")
    void listenToParitionWithOffset(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                    @Header(KafkaHeaders.OFFSET) int offset) {
        LOG.info("ListenToPartitionWithOffset [{}] from partition-{} with offset-{}", message, partition, offset);
    }

    @KafkaListener(topics = "topic-bytes")
    void listenerForRoutingTemplate(String message) {
        LOG.info("RoutingTemplate BytesListener [{}]", message);
    }

    @KafkaListener(topics = "topic-1")
    @SendTo("topic-2")
    String listenAndReply(String message) {
        LOG.info("ListenAndReply [{}]", message);
        return "This is a reply sent to 'topic-2' topic after receiving message at 'topic-others' topic";
    }

    @KafkaListener(id = "1", topics = "topic-message", groupId = "topic-user-mc", containerFactory = "kafkaJsonListenerContainerFactory")
    void listenerWithMessageConverter(Message message) {
        LOG.info("MessageConverterUserListener [{}]", message);
    }
}
