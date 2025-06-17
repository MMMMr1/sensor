package com.mikhalenok.monitor.statistics.infrastructure.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
