package com.example.autopartsmall.purchase.infrastructure.jpa;

import com.example.autopartsmall.purchase.domain.InboundTOLine;
import com.example.autopartsmall.purchase.infrastructure.convertor.PurchasePOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "inbound_to_line")
public class InboundTOLinePO {
    @Id
    @GeneratedValue
    Long id;

    Long purchaseOrderLineId;

    String materialId;
    Integer quantity;

    Long orderId;

    static InboundTOLinePO fromDomain(InboundTOLine orderLine) {
        return PurchasePOConverter.INSTANCE.toPO(orderLine);
    }

    InboundTOLine toDomain() {
        return PurchasePOConverter.INSTANCE.toDomain(this);
    }
}
