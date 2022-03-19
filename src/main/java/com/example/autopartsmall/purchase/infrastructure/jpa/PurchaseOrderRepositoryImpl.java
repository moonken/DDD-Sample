package com.example.autopartsmall.purchase.infrastructure.jpa;

import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.purchase.domain.PurchaseOrder;
import com.example.autopartsmall.purchase.domain.PurchaseOrderId;
import com.example.autopartsmall.purchase.domain.PurchaseOrderLineId;
import com.example.autopartsmall.purchase.domain.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {
    private PurchaseOrderDAO purchaseOrderDAO;
    private PurchaseOrderLineDAO purchaseOrderLineDAO;

    @Override
    public PurchaseOrder get(PurchaseOrderId id) {
        return getPO(id).toDomain();
    }

    @Override
    public PurchaseOrder save(PurchaseOrder order) {
        PurchaseOrderPO po = PurchaseOrderPO.fromDomain(order);
        // 先存储聚合根下面的实体
        purchaseOrderDAO.save(po);
        po.getOrderLines().forEach(l -> l.setOrderId(po.getId()));
        purchaseOrderLineDAO.saveAll(po.getOrderLines());


        return po.toDomain();
    }

    @Override
    public boolean exist(PurchaseOrderId purchaseOrderId) {
        return purchaseOrderDAO.existsById(purchaseOrderId.getValue());
    }

    @Override
    public boolean existOrderLineWithMaterial(PurchaseOrderId purchaseOrderId, PurchaseOrderLineId purchaseOrderLineId,
                                              MaterialId materialId) {
        return purchaseOrderLineDAO.existsByIdAndOrderIdAndMaterialId(purchaseOrderLineId.getValue(),
                purchaseOrderId.getValue(), materialId.getValue());
    }

    private PurchaseOrderPO getPO(PurchaseOrderId id) {
        return purchaseOrderDAO.findById(id.getValue()).orElseThrow(() -> new DomainEntityNotFoundException(id));
    }
}
