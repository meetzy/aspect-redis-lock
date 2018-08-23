package com.xfmeet.lock.redis.exception;

/**
 * @author meet
 */
public class LockException extends RuntimeException {
    public LockException(String message) {
        super(message);
    }
}
