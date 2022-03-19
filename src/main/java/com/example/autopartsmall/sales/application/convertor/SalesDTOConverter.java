package com.example.autopartsmall.sales.application.convertor;

import com.example.autopartsmall.sales.application.dto.SalesOrderDTO;
import com.example.autopartsmall.sales.application.dto.SalesOrderLineDTO;
import com.example.autopartsmall.sales.domain.SalesOrder;
import com.example.autopartsmall.sales.domain.SalesOrderLine;
import com.example.autopartsmall.sales.infrastructure.convertor.SalesIdConvertor;
import com.example.autopartsmall.sales.infrastructure.jpa.SalesOrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalesDTOConverter extends SalesIdConvertor {

    SalesDTOConverter INSTANCE = Mappers.getMapper(SalesDTOConverter.class);

    SalesOrderDTO toDTO(SalesOrderPO salesOrderPO);

    SalesOrderDTO toDTO(SalesOrder salesOrder);

    SalesOrderLine toDomain(SalesOrderLineDTO po);

    SalesOrderLineDTO toDTO(SalesOrderLine salesOrderLine);
}
