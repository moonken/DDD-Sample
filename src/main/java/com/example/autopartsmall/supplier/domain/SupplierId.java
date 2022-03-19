package com.example.autopartsmall.supplier.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class SupplierId extends EntityId<String> {
    @Default
    public SupplierId(String value) {
        super(value);
    }
}
