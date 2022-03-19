package com.example.autopartsmall.agency.infrastructure.convertor;

import com.example.autopartsmall.agency.domain.Agency;
import com.example.autopartsmall.agency.infrastructure.jpa.AgencyPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgencyPOConverter extends AgencyIdConvertor {
    AgencyPOConverter INSTANCE = Mappers.getMapper(AgencyPOConverter.class);

    Agency toDomain(AgencyPO agencyPO);
    AgencyPO toPO(Agency agency);
}
