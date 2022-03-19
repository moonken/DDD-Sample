package com.example.autopartsmall.purchase.infrastructure.convertor;

import com.example.autopartsmall.material.infrastructure.convertor.MaterialIdConvertor;
import com.example.autopartsmall.purchase.domain.InboundTOId;
import com.example.autopartsmall.purchase.domain.InboundTOLineId;
import com.example.autopartsmall.purchase.domain.PurchaseOrderId;
import com.example.autopartsmall.purchase.domain.PurchaseOrderLineId;
import com.example.autopartsmall.supplier.infrastructure.convertor.SupplierIdConvertor;

public interface PurchaseIdConvertor extends MaterialIdConvertor, SupplierIdConvertor {
    default PurchaseOrderLineId toPrimitive(Long id) {
        return new PurchaseOrderLineId(id);
    }

    default Long toPrimitive(PurchaseOrderLineId id) {
        return id == null ? null : id.getValue();
    }

    default InboundTOId toInboundTOId(Long id) {
        return new InboundTOId(id);
    }

    default Long toPrimitive(InboundTOId id) {
        return id == null ? null : id.getValue();
    }

    default InboundTOLineId toInboundTOLineId(Long id) {
        return new InboundTOLineId(id);
    }

    default Long toPrimitive(InboundTOLineId id) {
        return id == null ? null : id.getValue();
    }

    default PurchaseOrderId toPurchaseOrderId(Long id) {
        return new PurchaseOrderId(id);
    }

    default Long toPrimitive(PurchaseOrderId id) {
        return id == null ? null : id.getValue();
    }

}
