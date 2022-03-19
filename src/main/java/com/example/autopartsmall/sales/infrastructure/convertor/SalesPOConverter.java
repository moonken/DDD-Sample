package com.example.autopartsmall.sales.infrastructure.convertor;

import com.example.autopartsmall.sales.domain.SalesOrder;
import com.example.autopartsmall.sales.domain.SalesOrderLine;
import com.example.autopartsmall.sales.infrastructure.jpa.SalesOrderLinePO;
import com.example.autopartsmall.sales.infrastructure.jpa.SalesOrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalesPOConverter extends SalesIdConvertor {

    SalesPOConverter INSTANCE = Mappers.getMapper(SalesPOConverter.class);

    SalesOrder toDomain(SalesOrderPO po);

    SalesOrderPO toPO(SalesOrder salesOrder);

    SalesOrderLine toDomain(SalesOrderLinePO po);

    SalesOrderLinePO toPO(SalesOrderLine salesOrderLine);
}
