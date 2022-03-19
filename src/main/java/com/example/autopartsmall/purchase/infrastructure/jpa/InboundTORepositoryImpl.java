package com.example.autopartsmall.purchase.infrastructure.jpa;

import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.purchase.domain.InboundTO;
import com.example.autopartsmall.purchase.domain.InboundTOId;
import com.example.autopartsmall.purchase.domain.InboundTORepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InboundTORepositoryImpl implements InboundTORepository {
    private InboundTODAO inboundTODAO;
    private InboundTOLineDAO inboundTOLineDAO;

    @Override
    public InboundTO save(InboundTO order) {
        InboundTOPO po = InboundTOPO.fromDomain(order);
        // 先存储聚合根下面的实体
        inboundTODAO.save(po);
        po.getOrderLines().forEach(l -> l.setOrderId(po.getId()));
        inboundTOLineDAO.saveAll(po.getOrderLines());


        return po.toDomain();
    }

    private InboundTOPO getPO(InboundTOId id) {
        return inboundTODAO.findById(id.getValue()).orElseThrow(() -> new DomainEntityNotFoundException(id));
    }
}
