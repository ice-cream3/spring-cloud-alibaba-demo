### seata分布式事务
官网文档:https://seata.io/zh-cn/docs/next/overview/what-is-seata  

    Seata 是一款开源的分布式事务解决方案，致力于提供高性能和简单易用的分布式事务服务。  
    Seata 为用户提供了 AT、TCC、SAGA 和 XA 事务模式，为用户打造一站式的分布式解决方案。  
    其中​​AT​​​模式是​​Seata​​主推的模式，是基于二阶段提交来实现的。

​​术语：​​

TC (Transaction Coordinator) 事务协调者:维护全局和分支事务的状态，驱动全局事务提交或回滚。
TM (Transaction Manager) - 事务管理器:定义全局事务的范围：开始全局事务、提交或回滚全局事务。
RM (Resource Manager) - 资源管理器:管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。
-----------------------------------
    AT模式需要保证每个业务库，都有一张​​undo_log​​表，保存着业务数据执行前和执行后的镜像数据。


```
<!--添加seata依赖-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
</dependency>
```
application.yml配置
```
spring:
  cloud:
    alibaba:
      seata:
        # 配置事务分组.seata/seata/seata-1.3.0/script/config-center/config.txt文中自己定义的分组service.vgroupMapping.my_test_tx_group=default
        tx-service-group: my_test_tx_group
seata:
  registry:
    # 配置seata的注册中心,告诉 seata client 怎么云访问seata server(TC)
    type: nacos
    nacos:
      server-addr: 192.168.106.103:8850
      application: seata-server
      username: nacos
      password: nacos
      group: SEATA_GROUP
      namespace: 7bac0305-9683-42e5-9006-d3da093ce80f
  config:
    type: nacos
    nacos:
      server-addr: 192.168.106.103:8850
      username: nacos
      password: nacos
      group: SEATA_GROUP
      namespace: 7bac0305-9683-42e5-9006-d3da093ce80f
  tx-service-group: my_test_tx_group
  service:
    vgroup-mapping:
      my_test_tx_group: default
```
Controller请求方法添加@GlobalTransactional全局事务注解:  
```
    @GlobalTransactional
    @RequestMapping("/ts")
    public String ts(String name, BigDecimal amount) {
        logger.info("用户:{},获取金额:{}", name, amount);
        // 本地请求A
        testDao.create(name, amount);
        // 远程请求B
        payFeignService.add(name, amount);
        // 异常时A和B同时回滚
        int a = 1/0;
        return "订单金额:" + amount;
    }
```  
seata库中有三张表:
lock_table:
global_table:
branch_table:

![fire work](../fire_work.gif)