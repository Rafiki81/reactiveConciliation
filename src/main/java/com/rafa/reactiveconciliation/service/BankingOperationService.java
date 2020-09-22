package com.rafa.reactiveconciliation.service;

import com.rafa.reactiveconciliation.domain.BankingOperation;
import reactor.core.publisher.Flux;

public interface BankingOperationService {
    public Flux<BankingOperation> createBankingOperations(Flux<BankingOperation> bankingOperationFlux);
    public Flux<BankingOperation> listBankingOperations();
    public Flux<BankingOperation> reaconciliateBankingOperations(Flux<BankingOperation> bankingOperationFlux,double amountRange, int hoursRange);
    public Flux<BankingOperation> getReconciliated();
    public Flux<BankingOperation> getNonReconciliated();
}
