package com.example.autopartsmall.purchase.application;

import com.example.autopartsmall.common.ddd.ApplicationService;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.purchase.application.dto.InboundTOLineDTO;
import com.example.autopartsmall.purchase.application.dto.SubmitInboundTORequest;
import com.example.autopartsmall.purchase.domain.InboundTO;
import com.example.autopartsmall.purchase.domain.InboundTOLine;
import com.example.autopartsmall.purchase.domain.InboundTORepository;
import com.example.autopartsmall.purchase.domain.PurchaseOrderId;
import com.example.autopartsmall.purchase.domain.PurchaseOrderLineId;
import com.example.autopartsmall.purchase.domain.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationService
@AllArgsConstructor
@Transactional
public class InboundTOWriteService {
    private InboundTORepository repository;
    private PurchaseOrderService purchaseOrderService;

    public InboundTO submit(SubmitInboundTORequest request) {
        PurchaseOrderId purchaseOrderId = new PurchaseOrderId(request.getPurchaseOrderId());
        List<InboundTOLine> orderLines = request.getOrderLines().stream()
                .map(line -> this.createOrderLine(purchaseOrderId, line))
                .collect(Collectors.toList());
        InboundTO inboundTO = repository.save(InboundTO.newOrder(purchaseOrderId, orderLines, purchaseOrderService));

        purchaseOrderService.updateInboundStatus(purchaseOrderId);

        return inboundTO;
    }

    private InboundTOLine createOrderLine(PurchaseOrderId purchaseOrderId, InboundTOLineDTO line) {
        return InboundTO.newOrderLine(purchaseOrderId, new PurchaseOrderLineId(line.getPurchaseOrderLineId()),
                new MaterialId(line.getMaterialId()),
                line.getQuantity(), purchaseOrderService);
    }
}
