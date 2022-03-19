package com.example.autopartsmall.sales.domain;

import com.example.autopartsmall.common.ddd.exception.DomainException;

public class SalesOrderAlreadyConfirmedException extends DomainException {
    public SalesOrderAlreadyConfirmedException() {
        super("sales order already confirmed");
    }
}
