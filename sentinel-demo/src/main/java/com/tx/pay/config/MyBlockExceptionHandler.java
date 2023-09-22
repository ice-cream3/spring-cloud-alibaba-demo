package com.tx.pay.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MyBlockExceptionHandler
 * @Description:
 * @Author: ice
 * @Date: 2023/9/19 20:37
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyBlockExceptionHandler.class);

    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        logger.info("限流异常处理MyBlockExceptionHandler方法,rule:{}", e.getRule());
        JSONObject msg = new JSONObject();
        if (e instanceof FlowException) {
            msg.put("msg", "接口限流了");
        } else if (e instanceof DegradeException) {
            msg.put("msg", "服务降级了");
        } else if (e instanceof ParamFlowException) {
            msg.put("msg", "热点参数限流了");
        } else if (e instanceof SystemBlockException) {
            msg.put("msg", "系统保护规则限制了");
        } else if (e instanceof AuthorityException) {
            msg.put("msg", "授权规则限制了");
        }
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getWriter(), msg);
    }
}
