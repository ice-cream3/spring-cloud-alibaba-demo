### openfeign
```
<!--添加openfeign依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
application.yml配置
```
# feign日志局部配置
feign:
  client:
    config:
      pay-service:
        logger-level: full
        # 设置为默认的契约 (原生注解:默认契约 @RequestLine == @RequestMapping)
        #contract: feign.Contract.Default
        connect-timeout: 2000
        read-timeout: 3000
        request-interceptors:
          - com.tx.pay.interceptor.CustomerFeignInterceptor
```
启动类添加@EnableFeignClients  
创建feign服务类:
```
/**
 * value:指定接口服务名
 * path:指定接口类指定的@RequestMapping路径
 * configuration: 日志配置类
 */
@FeignClient(value = "pay-service", path = "/pay")
// 局部日志配置如下
// @FeignClient(value = "pay-service", path = "/pay", configuration = FeignConfig.class)
public interface PayFeignService {

    @RequestMapping("/error")
    String error(String payId);

    // 原生注解:默认契约 @RequestLine == @RequestMapping
    @RequestMapping("/get")
    String get(String payId);

    @RequestMapping("/add")
    String add(String payId);
    
    # 当rpc调用有多个参数时,参数需要加上@RequestParam(“xxx”),指定请求类型get、post
    @RequestMapping("/add")
    String create(@RequestParam(“payId”)String payId, @RequestParam(“num”)int num);
}
```
Controller中注入feign接口:  
```
    @Autowired  
    private PayFeignService payFeignService;
```  

如需指定业务拦截器,则可自定义拦截:com.tx.pay.interceptor.CustomerFeignInterceptor  
1.实现RequestInterceptor接口  
2.重写apply(RequestTemplate requestTemplate)方法

![fire work](./fire_work.gif)