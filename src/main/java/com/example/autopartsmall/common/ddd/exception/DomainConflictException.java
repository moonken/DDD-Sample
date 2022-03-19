package com.example.autopartsmall.common.ddd.exception;

public class DomainConflictException extends DomainException {
    public DomainConflictException(String errorMessage) {
        super(errorMessage);
    }
}
