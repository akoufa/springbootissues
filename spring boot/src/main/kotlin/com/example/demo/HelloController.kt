package com.example.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    private val logger: Logger = LoggerFactory.getLogger(HelloController::class.java)


    @GetMapping("/")
    suspend fun index(@RequestHeader("request-id") requestId: String): String {
        logger.debug("request-id from the reactor context is: $requestId")
        return "Spring Boot"
    }
}