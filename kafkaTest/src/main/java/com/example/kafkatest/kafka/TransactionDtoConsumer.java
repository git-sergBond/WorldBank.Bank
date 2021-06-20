package com.example.kafkatest.kafka;

import com.example.kafkatest.dto.TransactionDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Service
public class TransactionDtoConsumer {

    //TODO try read ConsumerRecord
    @KafkaListener(topics = "${spring.kafka.topics.atm-request}",
            groupId = "${spring.kafka.consumer.group-id}", //TODO make experiment this consumer group
            containerFactory = "singleFactory")
    public void payListener(@Payload TransactionDto message,
                            @Header(KafkaHeaders.CORRELATION_ID) String correlationId,
                            @Header(KafkaHeaders.REPLY_TOPIC) String replyToTopic,
                            @Header(KafkaHeaders.REPLY_PARTITION) byte[] replyToPartitionId
    ) {
        System.out.println("- - - - - - READ - - - - - - - - -");
        System.out.println("data: " + message);
        System.out.println("correlationId: " + correlationId);//TODO solve this problem with serialisation, and deserialization
        System.out.println("replyToTopic: " + replyToTopic);//TODO solve this problem with serialisation, and deserialization
        System.out.println("replyToPartitionId: " + ByteBuffer.wrap(replyToPartitionId).getInt());//TODO solve this problem with serialisation, and deserialization
    }

}
