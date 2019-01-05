package com.xfmeet.lock.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author meetzy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    
    /**
     * lock lockKey only
     */
    String lockKey() default "redis_lock_key";
    
    /**
     * is use wait get lock
     */
    boolean isTry() default false;
    
    /**
     * use try get lock need setting
     * wait get lock time
     */
    int waitTime() default 0;
    
    /**
     * auto unlock time
     */
    int quitTime() default 0;
    
    /**
     * time type
     */
    TimeUnit unitType() default TimeUnit.SECONDS;
    
    
}
