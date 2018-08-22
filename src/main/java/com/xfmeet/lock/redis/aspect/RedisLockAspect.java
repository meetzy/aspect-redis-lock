package com.xfmeet.lock.redis.aspect;


import com.xfmeet.lock.redis.annotation.RedisLock;
import com.xfmeet.lock.redis.utils.RedisLockService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author meet
 */
@Aspect
@Component
public class RedisLockAspect {

    @Autowired
    private RedisLockService redisLockService;

    @Pointcut("@annotation(com.xfmeet.lock.redis.annotation.RedisLock)")
    public void lockAspect() {

    }

    @Around("lockAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        RedisLock redisLock = getRedisLock(joinPoint);
        boolean tryRes = getRLock(redisLock);
        Object obj = null;
        if (tryRes) {
            try {
                obj = joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                redisLockService.unlock(redisLock.lockKey());
            }
        }
        return obj;
    }

    private boolean getRLock(RedisLock redisLock) {
        if (redisLock.isTry()) {
            return redisLockService.tryLock(redisLock.lockKey(), redisLock.unitType(), redisLock.waitTime(), redisLock.quitTime());
        } else {
            if (redisLock.quitTime() == 0) {
                return redisLockService.lock(redisLock.lockKey());
            }
            return redisLockService.lock(redisLock.lockKey(), redisLock.quitTime(), redisLock.unitType());
        }
    }

    private RedisLock getRedisLock(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(RedisLock.class);
    }
}
