package com.example.autopartsmall.sales.infrastructure.jpa;

import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.sales.domain.SalesOrder;
import com.example.autopartsmall.sales.domain.SalesOrderId;
import com.example.autopartsmall.sales.domain.SalesOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SalesOrderRepositoryImpl implements SalesOrderRepository {
    private SalesOrderDAO salesOrderDAO;
    private SalesOrderLineDAO salesOrderLineDAO;

    @Override
    public SalesOrder get(SalesOrderId id) {
        return getPO(id).toDomain();
    }

    @Override
    public SalesOrder save(SalesOrder salesOrder) {
        SalesOrderPO po = SalesOrderPO.fromDomain(salesOrder);
        // 先存储聚合根下面的实体
        salesOrderDAO.save(po);
        po.getOrderLines().forEach(l -> l.setOrderId(po.getId()));
        salesOrderLineDAO.saveAll(po.getOrderLines());

        return po.toDomain();
    }

    private SalesOrderPO getPO(SalesOrderId id) {
        return salesOrderDAO.findById(id.getValue()).orElseThrow(() -> new DomainEntityNotFoundException(id));
    }
}
