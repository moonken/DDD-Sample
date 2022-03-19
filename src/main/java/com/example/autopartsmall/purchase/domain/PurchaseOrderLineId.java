package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class PurchaseOrderLineId extends EntityId<Long> {
    @Default
    public PurchaseOrderLineId(Long value) {
        super(value);
    }
}
