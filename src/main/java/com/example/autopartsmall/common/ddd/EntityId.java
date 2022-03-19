package com.example.autopartsmall.common.ddd;

import lombok.Getter;

public abstract class EntityId<ID> extends ValueObject {
    @Getter
    protected final ID value;

    public EntityId(ID value) {
        this.assertNotNull(value, "null id value not allowed");
        this.value = value;
    }
}
