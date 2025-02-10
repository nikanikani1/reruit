package com.nika.recruit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${file.upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 确保路径以 / 结尾
        String uploadPath = uploadDir.endsWith("/") ? uploadDir : uploadDir + "/";
        // 确保使用 file:/// 前缀（Windows系统）或 file:/ （Linux系统）
        String location = "file:///" + uploadPath;
        
        registry.addResourceHandler("/images/**")
                .addResourceLocations(location)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

        System.out.println("静态资源映射：/images/** -> " + location);
    }
}