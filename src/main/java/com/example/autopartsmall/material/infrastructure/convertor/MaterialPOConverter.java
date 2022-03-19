package com.example.autopartsmall.material.infrastructure.convertor;

import com.example.autopartsmall.material.domain.Material;
import com.example.autopartsmall.material.infrastructure.jpa.MaterialPO;
import com.example.autopartsmall.supplier.infrastructure.convertor.SupplierIdConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialPOConverter extends MaterialIdConvertor, SupplierIdConvertor {
    MaterialPOConverter INSTANCE = Mappers.getMapper(MaterialPOConverter.class);

    Material toDomain(MaterialPO materialPO);
    MaterialPO toPO(Material material);
}
