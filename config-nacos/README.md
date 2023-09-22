### config

```
<!-- 添加config依赖 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

配置[bootstrap.yml],不理解为什么官网要单独指定在bootstrap.yml文件中配置nacos信息
```
spring:
  application:
    name: config-nacos
  cloud:
    nacos:
      server-addr: 192.168.106.103:8850
      username: nacos
      password: nacos
      config:
        file-extension: yaml
        group: test
        namespace: 2ce3b450-5f08-4fae-a38d-c7da174ddd15
```

启动类中动态打印nacos服务中的配置文件信息:
```
public static void main(String[] args) throws InterruptedException {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigNacosApplication.class, args);
    while(true) {
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.err.println("user name :" + userName + "; age: " + userAge);
        TimeUnit.SECONDS.sleep(1);
    }
}
```

Controller中动态打印nacos服务中的配置文件信息:  
用@Value("${user.name}")注入属性名称,添加@RefreshScope注解,动态刷新nacos中配置信息.
![fire smile](../fire_smile.gif)
