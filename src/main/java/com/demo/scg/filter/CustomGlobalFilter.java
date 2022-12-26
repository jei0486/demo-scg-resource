package com.demo.scg.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        log.info("======= [CustomGlobalFilter] custom global filter");
        log.info("Global filter Start : request id -> {}" , request.getId());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("======Global filter End == [{}] : response code -> {}", request.getId(),response.getStatusCode());
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
