package com.example.autopartsmall.sales.application;

import com.example.autopartsmall.agency.domain.AgencyId;
import com.example.autopartsmall.agency.domain.AgencyService;
import com.example.autopartsmall.common.ddd.ApplicationService;
import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.material.domain.Material;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.material.domain.MaterialRepository;
import com.example.autopartsmall.material.domain.MaterialService;
import com.example.autopartsmall.purchase.domain.PurchaseOrder;
import com.example.autopartsmall.purchase.domain.PurchaseOrderLine;
import com.example.autopartsmall.purchase.domain.PurchaseOrderService;
import com.example.autopartsmall.sales.application.dto.SubmitOrderRequest;
import com.example.autopartsmall.sales.domain.SalesOrder;
import com.example.autopartsmall.sales.domain.SalesOrderAlreadyConfirmedException;
import com.example.autopartsmall.sales.domain.SalesOrderId;
import com.example.autopartsmall.sales.domain.SalesOrderLine;
import com.example.autopartsmall.sales.domain.SalesOrderLineId;
import com.example.autopartsmall.sales.domain.SalesOrderRepository;
import com.example.autopartsmall.supplier.domain.SupplierId;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationService
@AllArgsConstructor
@Transactional
public class SalesOrderWriteService {
    private SalesOrderRepository repository;
    private MaterialService materialService;
    private AgencyService agencyService;

    private MaterialRepository materialRepository;
    private PurchaseOrderService purchaseOrderService;

    public SalesOrder create(SubmitOrderRequest request) {
        List<SalesOrderLine> orderLines = request.getOrderLines().stream()
                .map(line -> {
                    // TODO 用工厂保护OrderLine创建过程中的业务规则
                    MaterialId materialId = new MaterialId(line.getMaterialId());
                    materialService.validateExist(materialId);
                    return new SalesOrderLine(materialId, line.getQuantity());
                })
                .collect(Collectors.toList());
        // TODO 用工厂保护Order创建过程中的业务规则
        AgencyId agencyId = new AgencyId(request.getAgencyId());
        agencyService.validateExist(agencyId);
        SalesOrder salesOrder = new SalesOrder(agencyId, orderLines);
        return repository.save(salesOrder);
    }

    // TODO 用领域服务处理跨聚合操作
    public SalesOrder confirm(Long id) {
        SalesOrderId salesOrderId = new SalesOrderId(id);

        SalesOrder salesOrder = repository.get(salesOrderId);
        // TODO 将领域模型内的约束规则交给领域模型处理
        if (salesOrder.getStatus() != SalesOrder.Status.CREATED) {
            throw new SalesOrderAlreadyConfirmedException();
        }
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

    // TODO 非聚合根的操作由聚合根提供统一入口，保障不变性
    public SalesOrder modifyOrderLineQuantity(Long id, Long lineId, int quantity) {
        SalesOrderId salesOrderId = new SalesOrderId(id);
        SalesOrderLineId salesOrderLineId = new SalesOrderLineId(lineId);

        SalesOrder salesOrder = repository.get(salesOrderId);
        SalesOrderLine salesOrderLine = salesOrder.getOrderLines().stream()
                .filter(l -> l.getId().equals(lineId))
                .findFirst().orElseThrow(() -> new DomainEntityNotFoundException(new SalesOrderLineId(lineId)));
        salesOrderLine.modifyQuality(quantity);
        return repository.save(salesOrder);
    }
}
