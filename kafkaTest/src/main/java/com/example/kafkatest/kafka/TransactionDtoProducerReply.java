package com.example.kafkatest.kafka;

import com.example.kafkatest.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionDtoProducerReply {

    private final KafkaTemplate<String, TransactionDto> kafkaTemplate;

    public void reply(String key, TransactionDto message, String correlationId, String replyToTopic, Integer replyToPartitionId, boolean isOk) {
        ProducerRecord<String, TransactionDto> record = new ProducerRecord<>(replyToTopic, replyToPartitionId, key, message);
        record.headers()
                .add(KafkaHeaders.CORRELATION_ID, correlationId.getBytes())
                .add("X-is-ok", new byte[] { (byte)(isOk ? 1 : 0) });
        kafkaTemplate.send(record);

    }
}
