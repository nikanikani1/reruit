package com.nika.recruit.lock;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
    /**
     * 加锁
     * @param key
     * @param expire
     * @param timeUnit
     * @return
     */
    Boolean lock(String key, Integer expire, TimeUnit timeUnit);

    /**
     * 解锁
     * @param key
     * @return
     */
    Boolean unlock(String key);
}
