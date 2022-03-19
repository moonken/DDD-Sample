package com.example.autopartsmall.agency.domain;

import com.example.autopartsmall.common.ddd.DomainService;
import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import lombok.AllArgsConstructor;

@DomainService
@AllArgsConstructor
public class AgencyService {
    private AgencyRepository repository;

    public void validateExist(AgencyId agencyId) {
        if (!repository.exist(agencyId)) {
            throw new DomainEntityNotFoundException(agencyId);
        }
    }
}
