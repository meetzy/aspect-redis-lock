package com.xfmeet.lock.redis.exception;

/**
 * @author meetzy
 */
public class LockException extends RuntimeException {
    public LockException(String message) {
        super(message);
    }
}
