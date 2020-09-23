package com.rafa.reactiveconciliation.repositories;

import com.rafa.reactiveconciliation.domain.BankingOperation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BankingOperationReactiveRepository extends ReactiveCrudRepository<BankingOperation,Long> {

}
