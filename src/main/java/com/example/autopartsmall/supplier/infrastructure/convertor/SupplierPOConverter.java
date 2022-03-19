package com.example.autopartsmall.supplier.infrastructure.convertor;

import com.example.autopartsmall.supplier.domain.Supplier;
import com.example.autopartsmall.supplier.infrastructure.jpa.SupplierPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierPOConverter extends SupplierIdConvertor {
    SupplierPOConverter INSTANCE = Mappers.getMapper(SupplierPOConverter.class);

    Supplier toDomain(SupplierPO agencyPO);
    SupplierPO toPO(Supplier agency);
}
