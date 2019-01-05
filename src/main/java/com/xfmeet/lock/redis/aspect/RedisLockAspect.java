package com.xfmeet.lock.redis.aspect;


import com.xfmeet.lock.redis.annotation.RedisLock;
import com.xfmeet.lock.redis.exception.LockException;
import com.xfmeet.lock.redis.utils.RedisLockService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author meetzy
 */
@Aspect
@Order(Integer.MIN_VALUE)
@Component
public class RedisLockAspect {

    @Autowired
    private RedisLockService redisLockService;

    @Pointcut("@annotation(com.xfmeet.lock.redis.annotation.RedisLock)")
    public void lockAspect() {

    }

    @Around("lockAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RedisLock redisLock = getRedisLock(joinPoint);
        boolean tryRes = getRLock(redisLock);
        if (tryRes) {
            try {
                return joinPoint.proceed();
            } finally {
                redisLockService.unlock(redisLock.lockKey());
            }
        }
        return new LockException(joinPoint.getSignature().getName() + "method don`t get lock!");
    }

    private boolean getRLock(RedisLock r) {
        if (r.isTry()) {
            return redisLockService.tryLock(r.lockKey(), r.unitType(), r.waitTime(), r.quitTime());
        } else {
            if (r.quitTime() == 0) {
                return redisLockService.lock(r.lockKey());
            }
            return redisLockService.lock(r.lockKey(), r.quitTime(), r.unitType());
        }
    }

    private RedisLock getRedisLock(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(RedisLock.class);
    }
}
