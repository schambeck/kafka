package com.schambeck.kafka.service;

import com.schambeck.kafka.model.Invoice;
import com.schambeck.kafka.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repository;

    @Override
    @Transactional
    public Invoice create(Invoice invoice) {
        return repository.save(invoice);
    }

}
