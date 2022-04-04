package com.schambeck.kafka.consumer;

import com.schambeck.kafka.model.Invoice;
import com.schambeck.kafka.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class InvoiceCreatedConsumer {

    final InvoiceService service;

    @KafkaListener(concurrency = "${invoice-created.consumer.concurrency}", topics = "${invoice-created.topic}", groupId = "${invoice-created.consumer.group-id}", containerFactory = "invoiceCreatedKafkaListenerContainerFactory")
    void invoiceCreatedListener(Invoice invoice) {
        log.info("Invoice received: {}", invoice);
        service.create(invoice);
    }

}
