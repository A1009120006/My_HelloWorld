package com.nanning.gateWay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig {
	@Bean
	SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
		http.authorizeExchange().pathMatchers("/demo/**").hasRole("ADMIN").anyExchange().permitAll().and().cors().and()
				.httpBasic().and().csrf().disable();
		return http.build();
	}
}
