package com.schambeck.kafka.consumer;

import com.schambeck.kafka.model.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class InvoiceCreatedConsumer {

    @KafkaListener(concurrency = "5", topics = "${invoice-created.topic}", groupId = "invoiceCreatedGroup", containerFactory = "invoiceCreatedKafkaListenerContainerFactory")
    void invoiceCreatedListener(Invoice invoice) {
        log.info("Invoice created consumed: {}", invoice);
    }

}
