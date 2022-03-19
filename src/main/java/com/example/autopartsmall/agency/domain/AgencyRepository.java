package com.example.autopartsmall.agency.domain;

public interface AgencyRepository {
    Agency save(Agency material);
    boolean exist(AgencyId agencyId);
}
