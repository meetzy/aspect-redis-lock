package com.xfmeet.lock.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author meet
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * lock lockKey only
     *
     * @return
     */
    String lockKey();

    /**
     * is use wait get lock
     *
     * @return
     */
    boolean isTry() default false;

    /**
     * use try get lock need setting
     * wait get lock time
     *
     * @return
     */
    int waitTime() default 0;

    /**
     * auto unlock time
     *
     * @return
     */
    int quitTime() default 0;

    /**
     * time type
     *
     * @return
     */
    TimeUnit unitType() default TimeUnit.SECONDS;


}