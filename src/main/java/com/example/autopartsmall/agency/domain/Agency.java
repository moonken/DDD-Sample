package com.example.autopartsmall.agency.domain;

import com.example.autopartsmall.common.ddd.AggregateRoot;
import com.example.autopartsmall.common.support.Default;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 简化， 不处理sku级别和计量单位

// AllArgsConstructor 只提供给MapStructure PO转DO使用，其他则应走工厂方法
@AllArgsConstructor(onConstructor_={@Default})
// 禁用无参构造函数
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Agency extends AggregateRoot {
    private AgencyId id;

    private String name;
}
