package com.example.autopartsmall.sales.domain;

import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.common.ddd.exception.InvalidDomainObjectException;

public class InvalidSalesOrderException extends InvalidDomainObjectException {
    public InvalidSalesOrderException(DomainEntityNotFoundException notFoundException) {
        super("invalid sales order: " + notFoundException.getMessage());
    }
}
