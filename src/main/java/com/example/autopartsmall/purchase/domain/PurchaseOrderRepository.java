package com.example.autopartsmall.purchase.domain;

import com.example.autopartsmall.material.domain.MaterialId;

public interface PurchaseOrderRepository {
    PurchaseOrder get(PurchaseOrderId id);
    PurchaseOrder save(PurchaseOrder order);
    boolean exist(PurchaseOrderId purchaseOrderId);
    boolean existOrderLineWithMaterial(PurchaseOrderId purchaseOrderId, PurchaseOrderLineId purchaseOrderLineId, MaterialId materialId);
}
