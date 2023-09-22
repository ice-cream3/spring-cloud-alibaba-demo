package com.tx.ribbon;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @ClassName: MyRule
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 15:21
 */
public class MyRule extends AbstractLoadBalancerRule {

    /**
     * 自定义负载规则
     * @param key
     * @return
     */
    public Server choose(Object key) {
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        // 获取当前请求的服务实例
        List<Server> reachableServers = loadBalancer.getReachableServers();
        int nexted = ThreadLocalRandom.current().nextInt(reachableServers.size());
        return reachableServers.get(nexted);
    }

    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }
}
