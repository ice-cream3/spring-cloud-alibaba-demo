### LoadBalancer负载
[application.yml]配置ribbon:
```
<!--添加loadbalancer依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>

<!--排除ribbon-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
application.yml配置
```
spring:
  cloud:
    # 排除spring-cloud内置ribbon
    loadbalancer:
      ribbon:
        enabled: false
```
TemplateConfig添加@LoadBalanced
![fire work](./fire_work.gif)