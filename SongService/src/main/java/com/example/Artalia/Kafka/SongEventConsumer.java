package com.example.Artalia.Kafka;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.Artalia.Model.SongEventDto;
import com.example.Artalia.Service.SongService;

@Service
public class SongEventConsumer {
    @Autowired
    private SongService service;
    
    @KafkaListener(
        topics = "${spring.kafka.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(SongEventDto songEventDto) throws InterruptedException, ExecutionException{
        System.out.println("Check");
        service.updateMediaInfo(songEventDto).subscribe();
    }
}
