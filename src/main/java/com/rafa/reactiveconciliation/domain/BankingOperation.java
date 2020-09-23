package com.rafa.reactiveconciliation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;

@Data
@Document
public class BankingOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String date;
    private String customerId;
    private String account;
    private double amount;
    private boolean isReconciliated;

}

