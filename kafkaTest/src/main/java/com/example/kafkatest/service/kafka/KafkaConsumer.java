package com.example.kafkatest.service.kafka;

import com.example.kafkatest.dto.TransactionDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
/*
    @KafkaListener(topics = "${spring.kafka.topics.atm-request}",
            groupId = "${spring.kafka.consumer.group-id}") //TODO change consumer group
    public void payListener(@Payload Object message
                            //TODO this headers not work
                           // @Header(KafkaHeaders.CORRELATION_ID) int correlationId //TODO how to send this headers? this header unrecognized [payload={"payload":{"sourceId":0,"destinationId":0,"money":{"amount":"0.00","currencyType":"RUBLE"}},"headers":{"kafka_correlationId":1,"kafka_partitionId":2,"id":"93bf9c90-01a4-2ac8-5f91-5f7c42e3c6f6","timestamp":1623815704459}}, headers={kafka_offset=1, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@58571fef, kafka_timestampType=CREATE_TIME, kafka_receivedPartitionId=0, kafka_receivedTopic=atm-request, kafka_receivedTimestamp=1623815704459, __TypeId__=[B@66a8452a, kafka_groupId=world-bank}]
                           // @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) int correlationId //TODO is this correlationId ? OR MESSAGE_ID
                           // @Header(KafkaHeaders.RECEIVED_PARTITION_ID)int partitionId) { //TODO trey read and send msg to partition (search in bitbucket)
    ) {
        System.out.println("!!!" + message);
    }
*/

    @KafkaListener(topics = "${spring.kafka.topics.atm-request}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "singleFactory")
    public void payListener(TransactionDto message) {
        System.out.println("!!!" + message);
    }
}
