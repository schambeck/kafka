package com.schambeck.kafka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Invoice {

    private Long id;
    private LocalDate issued;
    private BigDecimal total;

}
