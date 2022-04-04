package com.schambeck.kafka.controller;

import com.schambeck.kafka.model.Invoice;
import com.schambeck.kafka.producer.InvoiceCreatedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoices")
@RequiredArgsConstructor
class InvoiceController {

    private final InvoiceCreatedProducer producer;

    @PostMapping
    ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        producer.sendMessage(invoice);
        return ResponseEntity.accepted().body(invoice);
    }

}
