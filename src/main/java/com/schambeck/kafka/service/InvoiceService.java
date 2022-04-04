package com.schambeck.kafka.service;

import com.schambeck.kafka.model.Invoice;

public interface InvoiceService {

    Invoice create(Invoice invoice);

}
