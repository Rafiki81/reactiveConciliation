package com.rafa.reactiveconciliation.handler;

import com.rafa.reactiveconciliation.domain.BankingOperation;
import com.rafa.reactiveconciliation.service.BankingOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Component
public class BankingOperationsHandler {

    @Autowired
    BankingOperationService bankingOperationService;

    public Mono<ServerResponse> list(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bankingOperationService.listBankingOperations(), BankingOperation.class);
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Flux<BankingOperation> bankingOperationFlux = request.bodyToFlux(BankingOperation.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bankingOperationService.createBankingOperations(bankingOperationFlux),BankingOperation.class);
    }

    public Mono<ServerResponse> reconciliate(ServerRequest request){
        Flux<BankingOperation> bankingOperationFlux = request.bodyToFlux(BankingOperation.class);
        Optional<String> amountRange = request.queryParam("amountRange");
        Optional<String> hoursRange = request.queryParam("amountRange");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bankingOperationService.reaconciliateBankingOperations(bankingOperationFlux,Double.parseDouble(amountRange.orElse("0.2")),Integer.parseInt(hoursRange.orElse("1"))),BankingOperation.class);
    }
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body("HelloWorld",String.class);
    }
}
