package com.example.autopartsmall.sales.infrastructure.jpa;

import com.example.autopartsmall.sales.domain.SalesOrderLine;
import com.example.autopartsmall.sales.infrastructure.convertor.SalesPOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "sales_order_line")
public class SalesOrderLinePO {
    @Id
    @GeneratedValue
    Long id;

    String materialId;

    Integer quantity;

    Long orderId;

    static SalesOrderLinePO fromDomain(SalesOrderLine salesOrderLine) {
        return SalesPOConverter.INSTANCE.toPO(salesOrderLine);
    }

    SalesOrderLine toDomain() {
        return SalesPOConverter.INSTANCE.toDomain(this);
    }
}
