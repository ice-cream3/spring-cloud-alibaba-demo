### 服务注册nacos
1.添加nacos依赖包  
```
<dependency>
    <groupId>com.alibaba.cloud</groupId>  
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>  
</dependency>
```
2.配置nacos
```
spring:
  application:
    name: order-service
  cloud:
    nacos:
      # nacos服务地址
      server-addr: 192.168.106.103:8850
      discovery:
        # nacos用户名
        username: nacos
        # nacos密码
        password: nacos
        # 注册空间
        namespace: 2ce3b450-5f08-4fae-a38d-c7da174ddd15
```
3.创建RestTemplate,注意要添加@LoadBalanced注解
```
@Configuration
public class TemplateConfig {

    @Bean
    // 因为注册到nacos时,nacos不会帮我们进行负载,所以需要加LoadBalanced
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
```
4.创建OrderController注入RestTemplate调用pay-nacos服务,请求事例
```
 // 注册到nacos,使用服务名称(pay-service)进行请求,restTemplate实例需要添加@LoadBalanced
 restTemplate.getForObject("http://pay-service/pay/get", String.class);
```
![fire smile](./fire_smile.gif)
