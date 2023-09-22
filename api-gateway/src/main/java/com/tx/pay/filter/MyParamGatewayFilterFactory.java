package com.tx.pay.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.GatewayToStringStyler;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: MyParamGatewayFilterFactory
 * @Description: 自定义过滤器(局部),参考:SetPathGatewayFilterFactory
 *  官网地址:https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gatewayfilter-factories
 * @Author: ice
 * @Date: 2023/9/21 18:46
 */
@Component
public class MyParamGatewayFilterFactory extends AbstractGatewayFilterFactory<MyParamGatewayFilterFactory.Config> {
    public static final String FILTER_NAME = "filterName";

    public MyParamGatewayFilterFactory() {
        super(Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("filterName");
    }

    public GatewayFilter apply(final Config config) {
        return new GatewayFilter() {
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest req = exchange.getRequest();
                MultiValueMap<String, String> queryParams = req.getQueryParams();
                List<String> names = queryParams.get(FILTER_NAME);
                if (queryParams.containsKey(FILTER_NAME) && null != names && names.contains(config.getFilterName())) {
                    return chain.filter(exchange.mutate().request(req).build());
                }
                exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                return exchange.getResponse().setComplete();
            }

            public String toString() {
                return GatewayToStringStyler.filterToStringCreator(MyParamGatewayFilterFactory.this).append("filterName", config.getFilterName()).toString();
            }
        };
    }

    public static class Config {
        private String filterName;

        public Config() {
        }

        public String getFilterName() {
            return filterName;
        }

        public void setFilterName(String filterName) {
            this.filterName = filterName;
        }
    }
}
