package com.example.kafkatest.config.kafka;


import com.example.kafkatest.dto.TransactionDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfig() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ConsumerConfig.GROUP_ID_CONFIG, groupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class
        );
    }

    @Bean
    public ConsumerFactory<Object, TransactionDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, TransactionDto> listenerFactory() {
        return new ConcurrentKafkaListenerContainerFactory<Object, TransactionDto>();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, TransactionDto> singleFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, TransactionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(false);
        factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, TransactionDto> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, TransactionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        factory.setMessageConverter(new BatchMessagingMessageConverter(converter()));
        return factory;
    }
}
