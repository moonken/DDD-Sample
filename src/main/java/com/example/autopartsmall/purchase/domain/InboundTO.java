package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.AggregateRoot;
import com.example.autopartsmall.common.ddd.DomainFactory;
import com.example.autopartsmall.common.support.Default;
import com.example.autopartsmall.material.domain.MaterialId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// AllArgsConstructor 只提供给MapStructure PO转DO使用，其他则应走工厂方法
@AllArgsConstructor(onConstructor_={@Default})
// 禁用无参构造函数
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class InboundTO extends AggregateRoot {
    public enum Status {
        CREATED, COMPLETE
    }

    private InboundTOId id;

    private PurchaseOrderId purchaseOrderId;

    private Status status;

    private List<InboundTOLine> orderLines;

    private InboundTO(PurchaseOrderId purchaseOrderId, List<InboundTOLine> orderLines) {
        this.assertNotNull(purchaseOrderId, "purchaseOrderId can not be null");
        this.assertNotEmpty(orderLines, "orderLines can not be empty");
        this.status = Status.CREATED;
        this.purchaseOrderId = purchaseOrderId;
        this.orderLines = orderLines;
    }

    @DomainFactory
    public static InboundTO newOrder(PurchaseOrderId purchaseOrderId, List<InboundTOLine> orderLines, PurchaseOrderService purchaseOrderService) {
        purchaseOrderService.validateExist(purchaseOrderId);
        return new InboundTO(purchaseOrderId, orderLines);
    }

    @DomainFactory
    public static InboundTOLine newOrderLine(PurchaseOrderId purchaseOrderId, PurchaseOrderLineId purchaseOrderLineId, MaterialId materialId, int quantity, PurchaseOrderService purchaseOrderService) {
        purchaseOrderService.validateInboundOrderLine(purchaseOrderId, purchaseOrderLineId, materialId);
        return new InboundTOLine(purchaseOrderLineId, materialId, quantity);
    }
}
