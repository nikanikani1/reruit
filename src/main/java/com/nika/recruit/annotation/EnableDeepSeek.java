package com.nika.recruit.annotation;

import com.nika.recruit.config.deepseek.DeepSeekConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ht
 * 是否启用AI能力
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DeepSeekConfigurer.class)
public @interface EnableDeepSeek {
}