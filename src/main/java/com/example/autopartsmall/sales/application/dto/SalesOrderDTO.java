package com.example.autopartsmall.sales.application.dto;

import com.example.autopartsmall.sales.application.convertor.SalesDTOConverter;
import com.example.autopartsmall.sales.domain.SalesOrder;
import com.example.autopartsmall.sales.infrastructure.jpa.SalesOrderPO;
import lombok.Data;

import java.util.List;

@Data
public class SalesOrderDTO {
    Long id;

    String agencyId;

    List<SalesOrderLineDTO> orderLines;

    SalesOrder.Status status;

    public static SalesOrderDTO fromDomain(SalesOrder salesOrder) {
        return SalesDTOConverter.INSTANCE.toDTO(salesOrder);
    }

    public static SalesOrderDTO fromPO(SalesOrderPO salesOrderPO) {
        return SalesDTOConverter.INSTANCE.toDTO(salesOrderPO);
    }
}
