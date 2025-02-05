package com.nika.recruit.config.deepseek;

import com.nika.recruit.annotation.EnableDeepSeek;
import com.nika.recruit.service.DeepSeekService;
import com.nika.recruit.service.impl.DeepSeekServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class DeepSeekConfigurer {

    @Bean
    @Conditional(OnDeepSeekEnabled.class)
    public DeepSeekService aiDeepSeekService() {
        return new DeepSeekServiceImpl();
    }
}