package com.schambeck.kafka.config;

import com.schambeck.kafka.model.Invoice;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;

@Configuration
class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    String bootstrapServers;

    @Value("${invoice-created.consumer.group-id}")
    String invoiceCreatedGroup;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Invoice> invoiceCreatedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Invoice> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(invoiceCreatedConsumerFactory());
        return factory;
    }

    ConsumerFactory<String, Invoice> invoiceCreatedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(GROUP_ID_CONFIG, invoiceCreatedGroup);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Invoice.class));
    }

}
