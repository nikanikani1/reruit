package com.nika.recruit.config.deepseek;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnDeepSeekEnabled implements Condition {


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        // 检查是否存在@EnableDeepSeek注解，如果存在则返回true
        return environment.getProperty("deepSeek.enabled", Boolean.class, false);
    }
}