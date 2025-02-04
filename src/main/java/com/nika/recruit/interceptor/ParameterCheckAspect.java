package com.nika.recruit.interceptor;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Order(3)
@Component
public class ParameterCheckAspect {

    @Around("execution(* com.nika.recruit.controller..*.*(..))")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        if (args.length > 0) {
            for (Object param : args) {
                Set<ConstraintViolation<Object>> checkResult = validator.validate(param);
                if (! CollectionUtils.isEmpty(checkResult)) {
                    List<String> resultMsg = checkResult.stream().map(e -> e.getMessage()).collect(Collectors.toList());
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,JSONUtil.toJsonStr(resultMsg));
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
