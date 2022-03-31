package com.example.autopartsmall.sales.domain;

import com.example.autopartsmall.common.ddd.DomainEventListener;
import com.example.autopartsmall.common.ddd.DomainEventPublisher;
import com.example.autopartsmall.common.ddd.DomainService;
import com.example.autopartsmall.material.domain.Material;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.material.domain.MaterialRepository;
import com.example.autopartsmall.purchase.domain.PurchaseOrder;
import com.example.autopartsmall.purchase.domain.PurchaseOrderLine;
import com.example.autopartsmall.purchase.domain.PurchaseOrderService;
import com.example.autopartsmall.supplier.domain.SupplierId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DomainService
@AllArgsConstructor
@DomainEventListener
@Slf4j
public class SalesOrderService {
    private SalesOrderRepository repository;
    private MaterialRepository materialRepository;
    private PurchaseOrderService purchaseOrderService;
    private DomainEventPublisher domainEventPublisher;

    public SalesOrder confirm(SalesOrderId salesOrderId) {
        SalesOrder salesOrder = repository.get(salesOrderId);
        // TODO 将领域模型内的约束规则交给领域模型处理
        salesOrder.confirmed();
        salesOrder = repository.save(salesOrder);

        splitAndPlacePurchaseOrders(salesOrder);

        return salesOrder;
    }

    private void splitAndPlacePurchaseOrders(SalesOrder salesOrder) {
        Map<SupplierId, List<SalesOrderLine>> splitResult = split(salesOrder);
        List<PurchaseOrder> purchaseOrders = splitResult.entrySet().stream()
                .map((this::buildPurchaseOrder))
                .collect(Collectors.toList());
        purchaseOrderService.saveAndPlaceOrders(purchaseOrders);
    }

    private PurchaseOrder buildPurchaseOrder(Map.Entry<SupplierId, List<SalesOrderLine>> supplierIdListEntry) {
        SupplierId supplierId = supplierIdListEntry.getKey();
        List<SalesOrderLine> orderLines = supplierIdListEntry.getValue();
        return PurchaseOrder.fromSplitSalesOrder(supplierId, orderLines.stream()
                .map(orderLine -> PurchaseOrderLine.fromSplitSalesOrder(orderLine.getMaterialId(), orderLine.getQuantity()))
                .collect(Collectors.toList()));
    }

    private Map<SupplierId, List<SalesOrderLine>> split(SalesOrder salesOrder) {
        List<MaterialId> materialIds = salesOrder.getOrderLines().stream()
                .map(SalesOrderLine::getMaterialId)
                .collect(Collectors.toList());
        Map<MaterialId, SupplierId> materialIdToSupplierId = materialRepository.get(materialIds).stream()
                .collect(Collectors.toMap(Material::getId, Material::getSupplierId));
        return salesOrder.getOrderLines().stream()
                .collect(Collectors.groupingBy(orderLine -> materialIdToSupplierId.get(orderLine.getMaterialId())));
    }
}
