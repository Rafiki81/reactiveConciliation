package com.rafa.reactiveconciliation.repositories;

import com.rafa.reactiveconciliation.domain.BankingOperation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BankingOperationReactiveRepository extends ReactiveMongoRepository<BankingOperation,Long> {

}
