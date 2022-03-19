package com.example.autopartsmall.purchase.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseOrderLineDAO extends JpaRepository<PurchaseOrderLinePO, Long>, JpaSpecificationExecutor<PurchaseOrderLinePO> {

    boolean existsByIdAndOrderIdAndMaterialId(Long id, Long orderId, String materialId);
}
