package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.exception.DomainException;

public class PurchaseOrderAlreadyPlacedException extends DomainException {
    public PurchaseOrderAlreadyPlacedException() {
        super("purchase order already placed");
    }
}
