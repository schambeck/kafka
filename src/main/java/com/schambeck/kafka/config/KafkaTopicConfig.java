package com.schambeck.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
class KafkaTopicConfig {

    @Value("${kafka.bootstrap-address}")
    String bootstrapAddress;

    @Value(value = "${invoice-created.topic}")
    String invoiceCreatedTopic;

    @Bean
    KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    NewTopic invoiceCreatedTopic() {
        return new NewTopic(invoiceCreatedTopic, 1, (short) 1);
    }

}
