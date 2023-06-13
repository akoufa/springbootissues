package com.example.springcloud

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.web.server.SecurityWebFilterChain
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

    fun convertStringToSecretKeyto(): SecretKey {
        val decodedKey: ByteArray = Base64.getDecoder().decode("WW91IGhhdmUgYSBkZWVwLCBkYXJrIGZlYXIgb2Ygc3BpZGVycywgY2lyY2EgMTk5MA==")
        return SecretKeySpec(decodedKey, 0, decodedKey.size, "AES")
    }

    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity,
    ): SecurityWebFilterChain {

        http.authorizeExchange { it.anyExchange().authenticated() }

        http.oauth2ResourceServer { oauthServer ->
            oauthServer.jwt {  }
        }

        http.csrf { it.disable() }
        return http.build()
    }
    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder {
        return NimbusReactiveJwtDecoder.withSecretKey(convertStringToSecretKeyto()).build()
    }

}