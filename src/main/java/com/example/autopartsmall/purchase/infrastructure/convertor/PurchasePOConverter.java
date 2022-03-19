package com.example.autopartsmall.purchase.infrastructure.convertor;

import com.example.autopartsmall.purchase.domain.InboundTO;
import com.example.autopartsmall.purchase.domain.InboundTOLine;
import com.example.autopartsmall.purchase.domain.PurchaseOrder;
import com.example.autopartsmall.purchase.domain.PurchaseOrderLine;
import com.example.autopartsmall.purchase.infrastructure.jpa.InboundTOLinePO;
import com.example.autopartsmall.purchase.infrastructure.jpa.InboundTOPO;
import com.example.autopartsmall.purchase.infrastructure.jpa.PurchaseOrderLinePO;
import com.example.autopartsmall.purchase.infrastructure.jpa.PurchaseOrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchasePOConverter extends PurchaseIdConvertor {

    PurchasePOConverter INSTANCE = Mappers.getMapper(PurchasePOConverter.class);

    PurchaseOrder toDomain(PurchaseOrderPO po);

    PurchaseOrderPO toPO(PurchaseOrder salesOrder);

    PurchaseOrderLine toDomain(PurchaseOrderLinePO po);

    PurchaseOrderLinePO toPO(PurchaseOrderLine salesOrderLine);

    InboundTOLinePO toPO(InboundTOLine orderLine);

    InboundTOLine toDomain(InboundTOLinePO inboundTOLinePO);

    InboundTO toDomain(InboundTOPO inboundTOPO);

    InboundTOPO toPO(InboundTO inboundTO);
}
