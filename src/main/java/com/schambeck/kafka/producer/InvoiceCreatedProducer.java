package com.schambeck.kafka.producer;

import com.schambeck.kafka.model.Invoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceCreatedProducer {

    @Value("${invoice-created.topic}")
    private String topic;

    private final KafkaTemplate<String, Invoice> kafkaTemplate;

    public void sendMessage(Invoice invoice) {
        ListenableFuture<SendResult<String, Invoice>> future = kafkaTemplate.send(topic, invoice);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(@Nullable SendResult<String, Invoice> result) {
                Objects.requireNonNull(result);
                log.info("Invoice created sent with offset {}: {}", result.getRecordMetadata().offset(), invoice);
            }

            @Override
            public void onFailure(@NonNull Throwable ex) {
                log.info("Unable to send message {} due to: {}", invoice, ex.getMessage());
            }
        });
    }

}
