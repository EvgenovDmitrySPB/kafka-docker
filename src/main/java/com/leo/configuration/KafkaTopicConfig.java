package com.leo.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic1(){
        return TopicBuilder.name("topic-1").build();
    }

    @Bean
    public NewTopic topic2(){
        return TopicBuilder.name("topic-2").build();
    }

    @Bean
    public NewTopic topicMessage(){
        return TopicBuilder.name("topic-message").build();
    }

    @Bean
    public NewTopic topicBytes(){
        return TopicBuilder.name("topic-bytes").build();
    }
}
