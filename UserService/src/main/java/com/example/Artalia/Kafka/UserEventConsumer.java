package com.example.Artalia.Kafka;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.Artalia.Controller.UserDatabaseController;
import com.example.Artalia.Model.UserEventDto;
import com.example.Artalia.Service.UserService;

@Service
public class UserEventConsumer {
    @Autowired
    private UserDatabaseController controller;
    
    @KafkaListener(
        topics = "${spring.kafka.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(UserEventDto userEventDto) throws InterruptedException, ExecutionException{
        System.out.println("Check");
        controller.updateImageByUserId(userEventDto.getId(), userEventDto).subscribe();
    }
}
