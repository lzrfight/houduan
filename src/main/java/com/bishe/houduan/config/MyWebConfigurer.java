package com.bishe.houduan.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class MyWebConfigurer {

    public void  addCorsMapping(CorsRegistry registry)
    {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
