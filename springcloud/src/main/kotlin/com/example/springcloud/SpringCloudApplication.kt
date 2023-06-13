package com.example.springcloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
class SpringCloudApplication

fun main(args: Array<String>) {
	Hooks.enableAutomaticContextPropagation()
	runApplication<SpringCloudApplication>(*args)
}
