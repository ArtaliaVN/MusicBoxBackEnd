package com.example.Artalia.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.producer1.topic}")
    private String topicName1;

    @Value("${spring.kafka.producer.producer2.topic}")
    private String topicName2;

    @Bean
    public NewTopic topic1(){
        return TopicBuilder.name(topicName1)
                .build();
    }

    @Bean
    public NewTopic topic2(){
        return TopicBuilder.name(topicName2)
                .build();
    }

}
