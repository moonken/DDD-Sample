package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class PurchaseOrderId extends EntityId<Long> {
    @Default
    public PurchaseOrderId(Long value) {
        super(value);
    }
}
