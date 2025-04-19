package com.example.Artalia.Event;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.Artalia.Dto.SongEventDto;
import com.example.Artalia.Dto.UserEventDto;

@Service
public class Producer {

    @Autowired
    @Qualifier("topic1")
    private NewTopic newTopic1;

    @Autowired
    @Qualifier("topic2")
    private NewTopic newTopic2;

    @Autowired
    private KafkaTemplate<String, UserEventDto> kafkaTemplate;

    public void sendUserEventMessage(UserEventDto userEventDto){
        Message<UserEventDto> message = MessageBuilder
            .withPayload(userEventDto)
            .setHeader(KafkaHeaders.TOPIC, newTopic1.name())
            .build();
        kafkaTemplate.send(message);
    }

    public void sendSongEventMessage(SongEventDto songEventDto){
        Message<SongEventDto> message = MessageBuilder
            .withPayload(songEventDto)
            .setHeader(KafkaHeaders.TOPIC, newTopic2.name())
            .build();
        kafkaTemplate.send(message);
    }
}
