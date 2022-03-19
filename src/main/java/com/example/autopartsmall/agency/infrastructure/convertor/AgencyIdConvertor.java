package com.example.autopartsmall.agency.infrastructure.convertor;

import com.example.autopartsmall.agency.domain.AgencyId;

public interface AgencyIdConvertor {
    default AgencyId toAgencyId(String id) {
        return new AgencyId(id);
    }

    default String toPrimitive(AgencyId id) {
        return id == null ? null : id.getValue();
    }
}
