package com.example.autopartsmall.common.ddd.exception;

public class DomainException extends RuntimeException {
    public DomainException(String errorMessage) {
        super(errorMessage);
    }
}
