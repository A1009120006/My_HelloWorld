package com.nanning.gateWay.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

 
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
        String token = exchange.getRequest().getQueryParams().getFirst("token");

    	log.info("======enter auth filter token:{}", token);
//        if (token == null || token.isEmpty()) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

}