package com.rafa.reactiveconciliation.router;

import com.rafa.reactiveconciliation.handler.BankingOperationsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(BankingOperationsHandler bankingOperationsHandler){
        return RouterFunctions
                .route(RequestPredicates.GET("/bankingOperations"),
                        bankingOperationsHandler::list)
                .andRoute(RequestPredicates.POST("/bankingOperations"), bankingOperationsHandler::create)
                .andRoute(RequestPredicates.POST("/bankingOperations/reconciliate"), bankingOperationsHandler::reconciliate);

    }
}
