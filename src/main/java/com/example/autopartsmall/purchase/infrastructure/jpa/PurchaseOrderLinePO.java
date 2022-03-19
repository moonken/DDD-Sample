package com.example.autopartsmall.purchase.infrastructure.jpa;

import com.example.autopartsmall.purchase.domain.PurchaseOrderLine;
import com.example.autopartsmall.purchase.infrastructure.convertor.PurchasePOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "purchase_order_line")
public class PurchaseOrderLinePO {
    @Id
    @GeneratedValue
    Long id;

    String materialId;

    Integer quantity;

    Long orderId;

    static PurchaseOrderLinePO fromDomain(PurchaseOrderLine orderLine) {
        return PurchasePOConverter.INSTANCE.toPO(orderLine);
    }

    PurchaseOrderLine toDomain() {
        return PurchasePOConverter.INSTANCE.toDomain(this);
    }
}
