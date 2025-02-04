package com.nika.recruit;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.nika.recruit.mapper")
@EnableScheduling
@Slf4j
public class RecruitApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RecruitApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String activeEnv = environment.getProperty("spring.profiles.active");
        log.info("current config yml : " + activeEnv);
        String port = environment.getProperty("server.port");
        log.info("swagger 接口测试访问地址 http://localhost:" + port + "/api/doc.html");
    }

}
