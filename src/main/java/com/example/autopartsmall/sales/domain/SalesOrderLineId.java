package com.example.autopartsmall.sales.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class SalesOrderLineId extends EntityId<Long> {
    @Default
    public SalesOrderLineId(Long value) {
        super(value);
    }
}
