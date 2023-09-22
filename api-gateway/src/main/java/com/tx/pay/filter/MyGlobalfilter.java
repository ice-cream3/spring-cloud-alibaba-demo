package com.tx.pay.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @classname: MyGlobalfilter
 * @description: 自定义全局过滤器
 * @author: ice
 * @date: 2023/9/21 20:30
 */
@Component
public class MyGlobalfilter implements GlobalFilter {

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        System.out.println("请求地址:"+ request.getPath().value()+",参数:"+request.getQueryParams());
        return chain.filter(exchange);
    }
}
