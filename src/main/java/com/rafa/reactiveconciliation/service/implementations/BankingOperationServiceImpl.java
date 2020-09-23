package com.rafa.reactiveconciliation.service.implementations;

import com.rafa.reactiveconciliation.domain.BankingOperation;
import com.rafa.reactiveconciliation.repositories.BankingOperationReactiveRepository;
import com.rafa.reactiveconciliation.service.BankingOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class BankingOperationServiceImpl implements BankingOperationService {

    @Autowired
    BankingOperationReactiveRepository bankingOperationReactiveRepository;

    @Override
    public Flux<BankingOperation> createBankingOperations(Flux<BankingOperation> bankingOperationFlux) {
        return bankingOperationReactiveRepository.saveAll(bankingOperationFlux);
    }

    @Override
    public Flux<BankingOperation> listBankingOperations() {
        return bankingOperationReactiveRepository.findAll();
    }

    @Override
    public Flux<BankingOperation> reaconciliateBankingOperations(Flux<BankingOperation> bankingOperationFlux, double amountRange, int hoursRange) {
        return Flux.fromStream(bankingOperationReactiveRepository.findAll()
                .filter(bankingOperation -> !bankingOperation.isReconciliated())
                .toStream()
                .filter(bankingOperation -> bankingOperationFlux.toStream()
                .anyMatch(bankingOperationToReconciliate -> bankingOperationToReconciliate.getCustomerId().equals(bankingOperation.getCustomerId()) &&
                        (bankingOperationToReconciliate.getAmount() >= (bankingOperation.getAmount() - amountRange)) && (bankingOperationToReconciliate.getAmount() <= (bankingOperation.getAmount() + amountRange)) ||
                        ((LocalDateTime.parse(bankingOperationToReconciliate.getDate()).isBefore(LocalDateTime.parse(bankingOperation.getDate()).plusHours(hoursRange))) &&
                                (LocalDateTime.parse(bankingOperation.getDate()).isAfter(LocalDateTime.parse(bankingOperation.getDate())))
                        )
                )));
    }

    @Override
    public Flux<BankingOperation> getReconciliated() {
        return bankingOperationReactiveRepository.findAll()
                .filter(BankingOperation::isReconciliated);
    }

    @Override
    public Flux<BankingOperation> getNonReconciliated() {
        return bankingOperationReactiveRepository.findAll()
                .filter(bankingOperation -> !bankingOperation.isReconciliated());
    }
}
