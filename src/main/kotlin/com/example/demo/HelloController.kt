package com.example.demo

import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Hooks
import reactor.core.publisher.Mono


@RestController
class HelloController {
    private val logger: Logger = LoggerFactory.getLogger(HelloController::class.java)

    init {
        Hooks.enableAutomaticContextPropagation()
    }

    @GetMapping("/")
    fun index(): Mono<String> {
        logger.trace("greet")
        return mono {
            val text = "Greetings from Spring Boot!"
            logger.error(text)
            text
        }
    }
}