package com.example.springcloud

import org.slf4j.Logger
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Configuration
class LoggingFilter {

    @Bean
    fun getLoggingGlobalGatewayFilter(
        logger: Logger,
        modifyRequestBodyFilter: ModifyRequestBodyGatewayFilterFactory,
    ): GlobalFilter = object : GlobalFilter, Ordered {
        override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
            logger.debug("request-id in LoggingFilter is: ${exchange.request.headers.getFirst("request-id")}")
            return chain.filter(exchange)
        }

        override fun getOrder() = Int.MIN_VALUE + 100
    }
}