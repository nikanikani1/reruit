package com.nika.recruit.lock;

import com.nika.recruit.base.ErrorCode;
import com.nika.recruit.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * 避免每个地方都去写分布式锁的加解锁代码
 */
@Component
@Slf4j
public class LockTemplateSupport {

    @Resource
    private DistributedLock distributedLock;

    public void lock(String key, Integer expire, TimeUnit timeUnit, Runnable runnable) {
        Boolean locked = distributedLock.lock(key, expire, timeUnit);
        if (! locked) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"请稍后重试");
        }
        try {
            log.info("lock key {}", key);
            runnable.run();
        } finally {
            log.info("unlock key {}", key);
            distributedLock.unlock(key);
        }
    }
}
