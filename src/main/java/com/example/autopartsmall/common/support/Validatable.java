package com.example.autopartsmall.common.support;

import org.springframework.util.ObjectUtils;

import java.security.InvalidParameterException;

public interface Validatable {
    default void assertNotEmpty(Object obj, String errorMessage) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new InvalidParameterException(errorMessage);
        }
    }

    default void assertNotNull(Object obj, String errorMessage) {
        if (obj == null) {
            throw new InvalidParameterException(errorMessage);
        }
    }
    default void assertMinValue(int obj, int value, String paramName) {
        if (obj < value) {
            throw new InvalidParameterException(paramName  + " should not less than " + value);
        }
    }
}
