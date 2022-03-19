package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class InboundTOLineId extends EntityId<Long> {
    @Default
    public InboundTOLineId(Long value) {
        super(value);
    }
}
