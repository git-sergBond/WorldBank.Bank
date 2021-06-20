package com.example.kafkatest.kafka;

import com.example.kafkatest.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Service
@RequiredArgsConstructor
public class TransactionDtoConsumer {

    private final TransactionDtoProducerReply replyProducer;

    //TODO try @SendTo for delete useless Producer
    //TODO try read ConsumerRecord
    @KafkaListener(topics = "${spring.kafka.topics.atm-request}",
            groupId = "${spring.kafka.consumer.group-id}", //TODO make experiment this consumer group
            containerFactory = "singleFactory")
    public void payListener(@Payload TransactionDto message,
               //TODO add             @Header(KafkaHeaders.MESSAGE_KEY) String messageKey,
                            @Header(KafkaHeaders.CORRELATION_ID) String correlationId,
                            @Header(KafkaHeaders.REPLY_TOPIC) String replyToTopic,
                            @Header(KafkaHeaders.REPLY_PARTITION) byte[] replyToPartitionId
    ) {
        Integer replyToPartitionIdInt = ByteBuffer.wrap(replyToPartitionId).getInt();//TODO solve this problem with serialisation, and deserialization

        System.out.println("- - - - - - READ - - - - - - - - -");
       // System.out.println("messageKey: " + messageKey);
        System.out.println("correlationId: " + correlationId);//TODO solve this problem with serialisation, and deserialization
        System.out.println("replyToTopic: " + replyToTopic);//TODO solve this problem with serialisation, and deserialization
        System.out.println("replyToPartitionId: " + replyToPartitionIdInt);
        System.out.println("data: " + message);

        replyProducer.reply("messageKey", message, correlationId, replyToTopic, replyToPartitionIdInt, true);
    }

}
