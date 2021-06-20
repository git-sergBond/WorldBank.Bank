package com.example.kafkatest.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfig {
    @Value("${kafka.bootstrapServers}")
    private String kafkaBootstrapServers;

    @Value("${kafka.topics.worldBank.request}")
    private String topicWorldBankRequest;

    @Bean
    KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        return new KafkaAdmin(config);
    }

    @Bean
    NewTopic atmActionTopic() {
        return new NewTopic(topicWorldBankRequest, 1, (short)1);
    }
}
