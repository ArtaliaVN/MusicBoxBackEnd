package com.example.Artalia.Kafka;

import org.springframework.kafka.annotation.KafkaListener;

import com.example.Artalia.Service.UserService;

@Service
public class UserEventConsumer {
    @Autowired
    private UserService userService;
    
    @KafkaListener(
        topics = "${spring.kafka.topic.name}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(UserEntity userEntity){
        userService.getRepo().save(userEntity);
    }
}
