package com.example.autopartsmall.material.domain;

import com.example.autopartsmall.common.ddd.AggregateRoot;
import com.example.autopartsmall.common.support.Default;
import com.example.autopartsmall.supplier.domain.SupplierId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// 简化， 不处理sku级别和计量单位

// AllArgsConstructor 只提供给MapStructure PO转DO使用，其他则应走工厂方法
@AllArgsConstructor(onConstructor_={@Default})
// 禁用无参构造函数
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Material extends AggregateRoot {
    private MaterialId id;

    private SupplierId supplierId;

    // 简化，不考虑货币
    private BigDecimal price;
}
