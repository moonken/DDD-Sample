package com.example.autopartsmall.material.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class MaterialId extends EntityId<String> {
    @Default
    public MaterialId(String value) {
        super(value);
    }
}
