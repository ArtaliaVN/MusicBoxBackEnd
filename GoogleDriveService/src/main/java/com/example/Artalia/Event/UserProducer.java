package com.example.Artalia.Event;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.Artalia.Dto.UserEventDto;

@Service
public class UserProducer {

    @Autowired
    private NewTopic newTopic;

    @Autowired
    private KafkaTemplate<String, UserEventDto> kafkaTemplate;

    public void sendMessage(UserEventDto userEventDto){
        Message<UserEventDto> message = MessageBuilder
            .withPayload(userEventDto)
            .setHeader(KafkaHeaders.TOPIC, newTopic.name())
            .build();
        kafkaTemplate.send(message);
    }
}
