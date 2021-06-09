package com.example.webapp.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topics.atm-request}",
            groupId = "${spring.kafka.consumer.group-id}") //TODO change consumer group
    public void payListener(@Payload String message,
                            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) int correlationId, //TODO is this correlationId ? OR MESSAGE_ID
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID)int partitionId) { //TODO trey read and send msg to partition (search in bitbucket)
        System.out.println("!!!" + message);
    }

}
