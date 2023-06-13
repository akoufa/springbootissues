package com.example.springcloud

import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.core.Ordered
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Configuration
class RequestIdGlobalGatewayFilter {

    @Bean
    @Scope("prototype")
    fun logger(injectionPoint: InjectionPoint): Logger {
        return LoggerFactory.getLogger(
            injectionPoint.methodParameter?.containingClass
                ?: injectionPoint.field?.declaringClass,
        )
    }

    var counter = 1L

    @Bean
    fun getRequestIdGlobalGatewayFilter(
        logger: Logger,
        modifyRequestBodyFilter: ModifyRequestBodyGatewayFilterFactory,
    ): GlobalFilter = object : GlobalFilter, Ordered {
        override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
            return mono {
                val requestId = "request-id/${counter++}"
                val mutatedRequest =
                    exchange.request.mutate().headers { httpHeaders -> httpHeaders.set("request-id", requestId) }
                        .build()
                chain.filter(exchange.mutate().request(mutatedRequest).build()).awaitSingleOrNull()
            }
        }

        override fun getOrder() = Int.MIN_VALUE
    }
}