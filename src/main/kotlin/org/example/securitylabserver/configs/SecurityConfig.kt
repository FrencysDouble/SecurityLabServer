package org.example.securitylabserver.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun springSecurityFilter(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth->
            auth.requestMatchers("/api/v1/users/**").permitAll()
                .requestMatchers("/api/v1/ui/**").permitAll()
                .anyRequest().authenticated()
        }
            .csrf { csrf -> csrf.disable() }
            .httpBasic(Customizer.withDefaults())

        return http.build()
    }
}