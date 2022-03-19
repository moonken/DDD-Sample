package com.example.autopartsmall.common.ddd.exception;

public class InvalidDomainObjectException extends DomainException {
    public InvalidDomainObjectException(String errorMessage) {
        super(errorMessage);
    }
}
