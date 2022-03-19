package com.example.autopartsmall.sales.domain;

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
public class SalesOrderLine extends DomainEntity<SalesOrderLineId> {

    private SalesOrderLineId id;

    private MaterialId materialId;

    private int quantity;

    public SalesOrderLine(MaterialId materialId, int quantity) {
        this.assertMinValue(quantity, 1, "quantity");
        this.materialId = materialId;
        this.quantity = quantity;
    }

    // TODO 对聚合内实体的操作，入口应放在聚合根
    public void modifyQuality(int quantity) {
        this.assertMinValue(quantity, 1, "quantity");
        this.quantity = quantity;
    }
}
