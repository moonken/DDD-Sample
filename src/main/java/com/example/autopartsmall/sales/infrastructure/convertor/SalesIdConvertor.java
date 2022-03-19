package com.example.autopartsmall.sales.infrastructure.convertor;

import com.example.autopartsmall.agency.infrastructure.convertor.AgencyIdConvertor;
import com.example.autopartsmall.material.infrastructure.convertor.MaterialIdConvertor;
import com.example.autopartsmall.sales.domain.SalesOrderId;
import com.example.autopartsmall.sales.domain.SalesOrderLineId;

public interface SalesIdConvertor extends MaterialIdConvertor, AgencyIdConvertor {
    default SalesOrderLineId toSalesOrderLineId(Long id) {
        return new SalesOrderLineId(id);
    }

    default Long toSalesOrderLineId(SalesOrderLineId id) {
        return id == null ? null : id.getValue();
    }

    default SalesOrderId toSalesOrderId(Long id) {
        return new SalesOrderId(id);
    }

    default Long toPrimitive(SalesOrderId id) {
        return id == null ? null : id.getValue();
    }
}
