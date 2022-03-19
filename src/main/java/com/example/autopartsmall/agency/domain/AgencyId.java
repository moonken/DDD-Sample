package com.example.autopartsmall.agency.domain;

import com.example.autopartsmall.common.ddd.EntityId;
import com.example.autopartsmall.common.support.Default;

public class AgencyId extends EntityId<String> {
    @Default
    public AgencyId(String value) {
        super(value);
    }
}
