package com.example.autopartsmall.purchase.infrastructure.jpa;

import com.example.autopartsmall.purchase.domain.PurchaseOrder;
import com.example.autopartsmall.purchase.infrastructure.convertor.PurchasePOConverter;
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
@Table(name = "purchase_order")
public class PurchaseOrderPO {
    @Id
    @GeneratedValue
    Long id;

    String supplierId;

    @Enumerated(EnumType.STRING)
    PurchaseOrder.Status status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    List<PurchaseOrderLinePO> orderLines;

    PurchaseOrder toDomain() {
        return PurchasePOConverter.INSTANCE.toDomain(this);
    }

    static PurchaseOrderPO fromDomain(PurchaseOrder salesOrder) {
        return PurchasePOConverter.INSTANCE.toPO(salesOrder);
    }
}
