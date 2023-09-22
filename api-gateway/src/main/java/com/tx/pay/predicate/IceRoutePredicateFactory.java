package com.tx.pay.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName: ICERoutePredicateFactory
 * @Description: 自定义断言
 * @Author: ice
 * @Date: 2023/9/21 17:47
 */
@Component
public class IceRoutePredicateFactory extends AbstractRoutePredicateFactory<IceRoutePredicateFactory.Config> {

    public static final String DATETIME_KEY = "loveYou";

    public IceRoutePredicateFactory() {
        super(Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("loveYou");
    }

    public Predicate<ServerWebExchange> apply(final Config config) {
        return new GatewayPredicate() {
            public boolean test(ServerWebExchange serverWebExchange) {
                String ask = "ILoveYou";
                return ask.equals(config.getLoveYou());
            }

            public String toString() {
                return String.format("ICE: %s", config.getLoveYou());
            }
        };
    }

    public static class Config {
        private @NotNull String loveYou;

        public Config() {
        }

        public String getLoveYou() {
            return loveYou;
        }

        public void setLoveYou(String loveYou) {
            this.loveYou = loveYou;
        }
    }
}
