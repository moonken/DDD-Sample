package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class InboundTOId extends EntityId<Long> {
    @Default
    public InboundTOId(Long value) {
        super(value);
    }
}
