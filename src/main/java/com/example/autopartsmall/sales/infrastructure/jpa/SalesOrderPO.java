package com.example.autopartsmall.sales.infrastructure.jpa;

import com.example.autopartsmall.sales.domain.SalesOrder;
import com.example.autopartsmall.sales.infrastructure.convertor.SalesPOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "sales_order")
public class SalesOrderPO {
    @Id
    @GeneratedValue
    Long id;

    String agencyId;

    @Enumerated(EnumType.STRING)
    SalesOrder.Status status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    List<SalesOrderLinePO> orderLines;

    SalesOrder toDomain() {
        return SalesPOConverter.INSTANCE.toDomain(this);
    }

    static SalesOrderPO fromDomain(SalesOrder salesOrder) {
        return SalesPOConverter.INSTANCE.toPO(salesOrder);
    }
}
