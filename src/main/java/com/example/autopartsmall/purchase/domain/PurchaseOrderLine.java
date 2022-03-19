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
public class PurchaseOrderLine extends DomainEntity<PurchaseOrderLineId> {

    private PurchaseOrderLineId id;

    private MaterialId materialId;

    private int quantity;

    private PurchaseOrderLine(MaterialId materialId, int quantity) {
        this.assertMinValue(quantity, 1, "quantity should not less than 1");
        this.materialId = materialId;
        this.quantity = quantity;
    }

    // 假设从销售订单拆单过来的采购订单不需要验证物料合法性
    public static PurchaseOrderLine fromSplitSalesOrder(MaterialId materialId, int quantity) {
        return new PurchaseOrderLine(materialId, quantity);
    }
}
