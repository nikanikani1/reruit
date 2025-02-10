package com.nika.recruit.lock;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 本地lock实现，后面切换到集群模式，直接替换此lock实现即可
 */
@Component
public class LocalLock implements DistributedLock {

    private Cache<String, Object> lockCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build();

    @Override
    public synchronized Boolean lock(String key, Integer expire, TimeUnit timeUnit) {
        if (lockCache.getIfPresent(key) == null) {
            lockCache.put(key, new Object());
            return true;
        }
        return false;
    }

    @Override
    public synchronized Boolean unlock(String key) {
        lockCache.invalidate(key);
        return true;
    }
}
