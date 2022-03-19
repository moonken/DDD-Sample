package com.example.autopartsmall.common.ddd;

import com.example.autopartsmall.common.support.Validatable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class DomainEntity<ID extends EntityId> implements Validatable {
    @EqualsAndHashCode.Include
    public abstract ID getId();
}
