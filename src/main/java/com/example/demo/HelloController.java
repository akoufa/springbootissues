package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    public HelloController() {
        Hooks.enableAutomaticContextPropagation();
    }

    @GetMapping("/")
    public Mono<String> index() {
        logger.error("greet");
        return Mono.just("Greetings from Spring Boot!").flatMap((greet) -> {
            logger.error(greet);
            return Mono.just("Greetings from Spring Boot!");
        });
    }

}
