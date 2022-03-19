package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.DomainEntity;
import com.example.autopartsmall.common.support.Default;
import com.example.autopartsmall.material.domain.MaterialId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// AllArgsConstructor 只提供给MapStructure PO转DO使用，其他则应走工厂方法
@AllArgsConstructor(onConstructor_={@Default})
// 禁用无参构造函数
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class InboundTOLine extends DomainEntity<InboundTOLineId> {
    private InboundTOLineId id;

    private InboundTOId orderId;

    private PurchaseOrderLineId purchaseOrderLineId;

    private MaterialId materialId;

    private int quantity;

    protected InboundTOLine(PurchaseOrderLineId purchaseOrderLineId, MaterialId materialId, int quantity) {
        this.assertMinValue(quantity, 1, "quantity");
        this.purchaseOrderLineId = purchaseOrderLineId;
        this.materialId = materialId;
        this.quantity = quantity;
    }
}
