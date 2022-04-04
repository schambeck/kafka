package com.schambeck.kafka.config;

import com.schambeck.kafka.model.Invoice;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    String bootstrapServers;

    @Bean
    KafkaTemplate<String, Invoice> invoiceCreatedKafkaTemplate() {
        return new KafkaTemplate<>(invoiceCreatedProducerFactory());
    }

    @Bean
    ProducerFactory<String, Invoice> invoiceCreatedProducerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

}
