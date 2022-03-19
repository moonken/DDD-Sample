package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.DomainEventListener;
import com.example.autopartsmall.common.ddd.DomainEventPublisher;
import com.example.autopartsmall.common.ddd.DomainService;
import com.example.autopartsmall.common.ddd.OnDomainEvent;
import com.example.autopartsmall.common.ddd.exception.DomainConflictException;
import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.material.domain.MaterialId;
import lombok.AllArgsConstructor;

import java.util.List;

@DomainService
@AllArgsConstructor
@DomainEventListener
public class PurchaseOrderService {
    private PurchaseOrderRepository repository;
    private DomainEventPublisher domainEventPublisher;
    private SupSystemGateway supplierSystemGateWay;

    public void saveAndPlaceOrders(List<PurchaseOrder> purchaseOrders) {
        purchaseOrders.forEach(this::saveAndPlaceOrder);
    }

    private void saveAndPlaceOrder(PurchaseOrder order) {
        PurchaseOrder savedOrder = repository.save(order);
        domainEventPublisher.publish(PurchaseOrderPlacedEvent.of(savedOrder.getId()));
    }

    @OnDomainEvent
    public void onPurchaseOrderPlaced(PurchaseOrderPlacedEvent event) {
        PurchaseOrder order = repository.get(event.getPurchaseOrderId());

        supplierSystemGateWay.placeOrder(order);

        order.orderPlaced();
        repository.save(order);
    }

    public void validateExist(PurchaseOrderId id) {
        if (!repository.exist(id)) {
            throw new DomainEntityNotFoundException(id);
        }
    }

    public void validateInboundOrderLine(PurchaseOrderId purchaseOrderId, PurchaseOrderLineId purchaseOrderLineId, MaterialId materialId) {
        if (!repository.existOrderLineWithMaterial(purchaseOrderId, purchaseOrderLineId, materialId)) {
            throw new DomainConflictException("invalid purchase order line: " + purchaseOrderLineId.getValue());
        }
    }

    public void updateInboundStatus(PurchaseOrderId purchaseOrderId) {
        PurchaseOrder order = repository.get(purchaseOrderId);
        order.received();
        repository.save(order);
    }
}
