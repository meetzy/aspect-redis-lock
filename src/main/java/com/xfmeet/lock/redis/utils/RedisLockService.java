package com.xfmeet.lock.redis.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
/**
 * @author meet
 */
@Component
public class RedisLockService {

    private static RedissonClient redissonClient;

    @Autowired
    public void setRedissonClient(RedissonClient locker) {
        redissonClient = locker;
    }

    /**
     * by lockKey to lock
     *
     * @param lockKey
     * @return
     */
    public boolean lock(String lockKey) {
        redissonClient.getLock(lockKey).lock();
        return true;

    }

    /**
     * by lockKey to unlock
     *
     * @param lockKey
     * @return
     */
    public void unlock(String lockKey) {
        redissonClient.getLock(lockKey).unlock();
    }

    /**
     * by lockKey and quitTime to lock
     *
     * @param lockKey
     * @param quitTime
     * @param unitType
     * @return
     */
    public boolean lock(String lockKey, int quitTime, TimeUnit unitType) {
        redissonClient.getLock(lockKey).lock(quitTime, unitType);
        return true;
    }

    /**
     * try get lock have waitTime
     *
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param quitTime
     * @return
     */
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int quitTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, quitTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }
}