package com.cheng.springcloud.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route-name路由规则
     * 当访问地址http://localhost:9527/guonei时自动转发地址为:
     * http://news.baidu.com/guonei
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_cheng",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return  routes.build();
    }


    @Bean
    public RouteLocator customerRouteLocator1(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_bo",
                r->r.path("/guoji")
                        .uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
