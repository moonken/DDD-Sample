package com.example.autopartsmall.sales.domain;

import com.example.autopartsmall.common.ddd.DomainEntity;
import com.example.autopartsmall.common.support.Default;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.material.domain.MaterialService;
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

    protected SalesOrderLine(MaterialId materialId, int quantity, MaterialService materialService) {
        this.assertMinValue(quantity, 1, "quantity");
        materialService.validateExist(materialId);
        this.materialId = materialId;
        this.quantity = quantity;
    }

    protected void modifyQuality(int quantity) {
        this.assertMinValue(quantity, 1, "quantity");
        this.quantity = quantity;
    }
}
