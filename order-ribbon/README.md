### 负载均衡Ribbon
[application.yml]配置ribbon:
```
ribbon:
  eager-load:
    # 关闭延迟加载
    enabled: true
    # 多个服务用逗号分隔
    clients: pay-service
```
自定义路由规则:
    com.tx.ribbon.RibbonRandomRuleConfig  
启动类添加配置:指定pay-service服务路由规则  
```
@RibbonClients(value = {
        @RibbonClient(name = "pay-service", configuration = RibbonRandomRuleConfig.class)
})
```
自定义负载规则:
    com.tx.ribbon.MyRule  
![fire work](../fire_work.gif)