package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.common.ddd.AggregateRoot;
import com.example.autopartsmall.common.ddd.DomainFactory;
import com.example.autopartsmall.common.support.Default;
import com.example.autopartsmall.supplier.domain.SupplierId;
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
public class PurchaseOrder extends AggregateRoot {
    public enum Status {
        CREATED, PLACED, RECEIVED
    }

    private PurchaseOrderId id;

    private SupplierId supplierId;

    private Status status;

    private List<PurchaseOrderLine> orderLines;

    private PurchaseOrder(SupplierId supplierId, List<PurchaseOrderLine> orderLines) {
        this.assertNotNull(supplierId, "supplierId can not be null");
        this.assertNotEmpty(orderLines, "orderLines can not be empty");
        this.status = Status.CREATED;
        this.supplierId = supplierId;
        this.orderLines = orderLines;
    }

    // 假设从销售订单拆单过来的采购订单不需要验证物料合法性
    @DomainFactory
    public static PurchaseOrder fromSplitSalesOrder(SupplierId supplierId, List<PurchaseOrderLine> orderLines) {
        return new PurchaseOrder(supplierId, orderLines);
    }

    public void orderPlaced() {
        if (this.status != Status.CREATED) {
            throw new PurchaseOrderAlreadyPlacedException();
        }
        this.status = Status.PLACED;
    }

    public void received() {
        this.status = Status.RECEIVED;
    }
}
