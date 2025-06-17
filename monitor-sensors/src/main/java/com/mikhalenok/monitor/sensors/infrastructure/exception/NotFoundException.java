package com.mikhalenok.monitor.sensors.infrastructure.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
