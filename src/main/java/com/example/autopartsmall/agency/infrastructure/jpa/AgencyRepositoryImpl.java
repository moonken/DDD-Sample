package com.example.autopartsmall.agency.infrastructure.jpa;

import com.example.autopartsmall.agency.domain.Agency;
import com.example.autopartsmall.agency.domain.AgencyId;
import com.example.autopartsmall.agency.domain.AgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AgencyRepositoryImpl implements AgencyRepository {
    private AgencyDAO agencyDAO;

    @Override
    public Agency save(Agency material) {
        return agencyDAO.save(AgencyPO.fromDomain(material)).toDomain();
    }

    @Override
    public boolean exist(AgencyId agencyId) {
        return agencyDAO.existsById(agencyId.getValue());
    }
}
