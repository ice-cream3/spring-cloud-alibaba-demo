### spring cloud alibaba gateway网关
*  官网文档:https://spring.io/projects/spring-cloud-gateway#learn  

```
<!--添加 gateway 依赖-->
 <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```
application.yml配置
```
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      # 路由规则,如果启动报错就把注释文档删除
      routes:
        - id: order_route # 路由的唯一标识
          # 需要转发的地址,直接写死的
          #uri: http://localhost:8083
          # 需要转发的地址,从nacos中获取服务名称
          uri: lb://order-service
          # 断言规则 路由规则匹配
          predicates:
            # 设置一个路由地址,请求时添加到域名后,接口地址前http://localhost:8095/路由地址/pay/add,如:http://localhost:8095/order-route/order/add?orderId=123
            - Path=/order-route/**
            # 官网文档:https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories
            # This route matches any request made after Jan 20, 2017 17:42 Mountain Time (Denver). 不符合规则会返回404
            - After=2022-09-21T17:21:23.192+08:00[Asia/Shanghai]
            # 自定义断言IceRoutePredicateFactory
            #- Ice=ILoveYour
          filters:
            - StripPrefix=1 #转发前去掉指定的路由地址:order-route变成这样:http://localhost:8083/pay/add
            - MyParam=filter #自定义过滤
#      globalcors:
#        cors-configurations:
#          '[/**]': # 允许跨域访问资源
#            allowedOrigins:
#              - *
#            allowedMethods:
#              - GET
```

#### 配置网关规则:
```
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      # 路由规则,如果启动报错就把注释文档删除
      routes:
        - id: order_route # 路由的唯一标识
          # 需要转发的地址,直接写死的
          #uri: http://localhost:8083
          # 需要转发的地址,从nacos中获取服务名称
          uri: lb://order-service
          # 断言规则 路由规则匹配
          predicates:
            # 设置一个路由地址,请求时添加到域名后,接口地址前http://localhost:8095/路由地址/pay/add,如:http://localhost:8095/order-route/order/add?orderId=123
            - Path=/order-route/**
```
请求路径:  
    http://localhost:8095/order-route/order/add?orderId=123  
    发送请求时会根据配置的断言路径:(Path=/order-route/**)中转到order-service的/order/add方法,实际就是把符合/order-route的路径去掉.添加上要请求的服务,再发送请求.

----------------------------------------------------------------

#### 配置网关断言和自定义断言:
```
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        - id: order_route
          uri: lb://order-service
          # 断言规则 路由规则匹配
          predicates:
            # 设置一个路由地址,请求时添加到域名后,接口地址前http://localhost:8095/路由地址/pay/add,如:http://localhost:8095/order-route/order/add?orderId=123
            - Path=/order-route/**
            # 官网文档:https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories
            # This route matches any request made after Jan 20, 2017 17:42 Mountain Time (Denver). 不符合规则会返回404
            - After=2022-09-21T17:21:23.192+08:00[Asia/Shanghai]
            # 自定义断言IceRoutePredicateFactory
            #- Ice=ILoveYour
```

实现自定义断言类:com.tx.pay.predicate.IceRoutePredicateFactory  

```
断言类继承AbstractRoutePredicateFactory 重写apply方法,定义内部配置类Config,如下:
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
```
*  官网支持的断言规则文档:  
https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories  

* 配置的断言规则:- After=2022-09-21T17:21:23.192+08:00[Asia/Shanghai]  
请求的当前时间必须在2022-09-21T17:21:23.192+08:00之后.
> [!WARNING]
> 在指定时间之前请求则返回**404**

时间格式必须是ZonedDateTime,比如定义一个未来的时间.用于实现抢购业务等.  
输出时间:ZonedDateTime now = ZonedDateTime.now();

自定义断言配置的请求规则必须等于:ILoveYour,否则请求返回**404**

---------------------------------------------------------

#### 过虑器配置和自定义过滤器:
```
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        - id: order_route
          uri: lb://order-service
          predicates:
            - Path=/order-route/**
          filters:
            - StripPrefix=1 #转发前去掉指定的路由地址:order-route变成这样:http://localhost:8083/pay/add
            - MyParam=filter #自定义过滤
```

实现自定义过滤器: com.tx.pay.filter.MyParamGatewayFilterFactory  
参考官网过滤器:SetPathGatewayFilterFactory
*  官网地址:https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gatewayfilter-factories

```
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
```
* 配置过滤规则: - MyParam=filter
* 自定义配置类中定义的参数名称: filterName  
请求路径:  
http://localhost:8095/order-route/order/add?orderId=123&filterName=filter
发送请求时如果没有filterName参数或者filterName的value值不等于filter则返回***404***


![fire work](../fire_work.gif)