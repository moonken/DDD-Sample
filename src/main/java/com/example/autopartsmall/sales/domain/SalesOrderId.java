package com.example.autopartsmall.sales.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class SalesOrderId extends EntityId<Long> {
    @Default
    public SalesOrderId(Long value) {
        super(value);
    }
}
