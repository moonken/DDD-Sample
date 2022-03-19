package com.example.autopartsmall.sales.application.dto;

import com.example.autopartsmall.sales.application.convertor.SalesDTOConverter;
import com.example.autopartsmall.sales.domain.SalesOrderLine;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class SalesOrderLineDTO {
    String materialId;

    @Min(1)
    int quantity;

    public SalesOrderLine toOrderLine() {
        return SalesDTOConverter.INSTANCE.toDomain(this);
    }
}
