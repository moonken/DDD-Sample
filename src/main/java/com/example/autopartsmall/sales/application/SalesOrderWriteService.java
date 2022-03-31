package com.example.autopartsmall.sales.application;

import com.example.autopartsmall.agency.domain.AgencyId;
import com.example.autopartsmall.agency.domain.AgencyService;
import com.example.autopartsmall.common.ddd.ApplicationService;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.material.domain.MaterialService;
import com.example.autopartsmall.sales.application.dto.SalesOrderLineDTO;
import com.example.autopartsmall.sales.application.dto.SubmitOrderRequest;
import com.example.autopartsmall.sales.domain.SalesOrder;
import com.example.autopartsmall.sales.domain.SalesOrderId;
import com.example.autopartsmall.sales.domain.SalesOrderLine;
import com.example.autopartsmall.sales.domain.SalesOrderLineId;
import com.example.autopartsmall.sales.domain.SalesOrderRepository;
import com.example.autopartsmall.sales.domain.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationService
@AllArgsConstructor
@Transactional
public class SalesOrderWriteService {
    private SalesOrderRepository repository;
    private SalesOrderService salesOrderService;
    private MaterialService materialService;
    private AgencyService agencyService;

    public SalesOrder create(SubmitOrderRequest request) {
        List<SalesOrderLine> orderLines = request.getOrderLines().stream()
                .map(this::createOrderLine)
                .collect(Collectors.toList());
        // TODO 用工厂保护Order创建过程中的业务规则
        SalesOrder salesOrder = SalesOrder.newOrder(new AgencyId(request.getAgencyId()), orderLines, agencyService);
        return repository.save(salesOrder);
    }

    public SalesOrder confirm(Long id) {
        // TODO 用领域服务处理跨聚合操作
        return salesOrderService.confirm(new SalesOrderId(id));
    }

    private SalesOrderLine createOrderLine(SalesOrderLineDTO line) {
        // TODO 用工厂保护OrderLine创建过程中的业务规则
        return SalesOrder.newOrderLine(new MaterialId(line.getMaterialId()), line.getQuantity(), materialService);
    }

    // TODO 非聚合根的操作由聚合根提供统一入口，保障不变性
    public SalesOrder modifyOrderLineQuantity(Long id, Long lineId, int quantity) {
        SalesOrder salesOrder = repository.get(new SalesOrderId(id));
        salesOrder.modifyOrderLineQuantity(new SalesOrderLineId(lineId), quantity);
        return repository.save(salesOrder);
    }
}
