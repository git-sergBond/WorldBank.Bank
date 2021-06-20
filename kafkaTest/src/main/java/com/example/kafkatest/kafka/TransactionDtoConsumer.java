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

    private final TransactionDtoReplyProducer replyProducer;

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


    /* TODO TRY this way

    //TODO try @SendTo for delete useless Producer
    //TODO try read Message<TransactionDto>
    @KafkaListener(topics = "${spring.kafka.topics.atm-request}",
            groupId = "${spring.kafka.consumer.group-id}", //TODO make experiment with multiple consumer group
            containerFactory = "singleFactory")
    public void payListener(ConsumerRecord<String, TransactionDto> record) {

        String key = record.key();
        TransactionDto data = record.value();

        //TODO solve this problem with serialisation, and deserialization
        String correlationId = new String(record.headers().lastHeader(KafkaHeaders.CORRELATION_ID).value());
        String replyToTopic = new String(record.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value());
        Integer replyToPartitionIdInt = ByteBuffer.wrap(record.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value()).getInt();
        //TODO solve this problem with serialisation, and deserialization

        System.out.println("- - - - - - READ - - - - - - - - -");
        System.out.println("key: " + key);
        System.out.println("correlationId: " + correlationId);
        System.out.println("replyToTopic: " + replyToTopic);
        System.out.println("replyToPartitionId: " + replyToPartitionIdInt);
        System.out.println("data: " + data);


        //TODO ERROR replyProducer.reply(record.key(), dto, correlationId, replyToTopic, replyToPartitionIdInt, true);

        ProducerRecord<String, TransactionDto> replRecord = new ProducerRecord<>(replyToTopic, replyToPartitionIdInt, key, data);
        replRecord.headers()
                .add(KafkaHeaders.CORRELATION_ID, correlationId.getBytes())
                .add("X-is-ok", new byte[] { 1 });
        kafkaTemplate.send(replRecord);
    }
*/
}
