package com.schambeck.kafka.repository;

import com.schambeck.kafka.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
