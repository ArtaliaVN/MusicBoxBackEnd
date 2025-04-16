// package com.example.Artalia.Kafka;

// import org.apache.kafka.clients.admin.NewTopic;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.support.KafkaHeaders;
// import org.springframework.messaging.Message;
// import org.springframework.messaging.support.MessageBuilder;
// import org.springframework.stereotype.Service;

// import com.example.Artalia.Data.UserAuthEntity;
// import com.example.Artalia.Model.MessageResponse;

// @Service
// public class AuthProducer {
//     private NewTopic topic;

//     private KafkaTemplate<String, UserAuthEntity> kafkaTemplate;
    
//     public AuthProducer(NewTopic topic, KafkaTemplate<String, UserAuthEntity> kafkaTemplate){
//         this.topic = topic;
//         this.kafkaTemplate = kafkaTemplate;
//     }

//     public MessageResponse sendMessage(UserAuthEntity event){
//         Message<UserAuthEntity> message = MessageBuilder
//             .withPayload(event)
//             .setHeader(KafkaHeaders.TOPIC, topic.name())
//             .build();
//         kafkaTemplate.send(message);
//         return new MessageResponse("Successful");
//     }
// }
