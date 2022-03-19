package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public class PurchaseOrderPlacedEvent extends DomainEvent {
    private PurchaseOrderId purchaseOrderId;
}
