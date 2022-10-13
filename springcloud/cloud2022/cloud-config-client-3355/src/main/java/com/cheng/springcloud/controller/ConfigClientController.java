package com.cheng.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   //为了解决配置中心修改后，客户端每次需要重新启动才能获取配置中心的信息，yml中暴漏接口
public class ConfigClientController {

    @Value("${server.port}")
    String serverPort;
    @Value("${config.info}")
    String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return "信息为:"+configInfo+"端口号为:"+serverPort;
    }
}
