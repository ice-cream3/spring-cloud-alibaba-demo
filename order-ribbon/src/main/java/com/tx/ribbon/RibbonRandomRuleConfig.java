package com.tx.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RibbonRandomRuleConfig
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 13:04
 */
@Configuration
public class RibbonRandomRuleConfig {

    /**
     * config规则不能写在启动方法同一目录下.同目录下会全局生效
     * @return
     */
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
