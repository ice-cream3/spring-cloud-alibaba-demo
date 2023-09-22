### sentinel限流

- 添加依赖
```
<!-- 添加 sentinel 依赖-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```
- 配置[application.yml]
```
spring:
  application:
    name: sentinel-openfeign
  cloud:
    sentinel:
      transport:
        # 服务地址
        dashboard: 127.0.0.1:8080 
      # 针对链路的限流,需要设置此参数:表示维护一个链路树结构 
      # 流控-->高级-->链路配置时帮我们自动维护一个调用链路
      web-context-unify: false
```
- 自定义降级影响类:com.tx.pay.feign.PayFeignServiceFallback
```
@Component
public class PayFeignServiceFallback implements PayFeignService{
    public String get(String payId) {
        return "降级,降级";
    }
    public String add(String payId) {
        return "降级了,降级了";
    }

    public String error(String payId) {
        return "降级了,降级了";
    }
}
```

>实现降级的feignService接口,定义返回的方法,方法的参数和返回值必须跟实现的feignService中方法一样.  
>在feignService类中指定fallback类:  
>@FeignClient(value = "pay-service", path = "/pay", fallback = PayFeignServiceFallback.class)

![fire smile](../fire_smile.gif)