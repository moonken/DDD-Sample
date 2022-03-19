package com.example.autopartsmall.purchase.application.dto;

import com.example.autopartsmall.purchase.application.convertor.PurchaseDTOConverter;
import com.example.autopartsmall.purchase.domain.InboundTO;
import lombok.Data;

import java.util.List;

@Data
public class InboundTODTO {
    Long id;

    String purchaseOrderId;

    List<InboundTOLineDTO> orderLines;

    InboundTO.Status status;

    public static InboundTODTO fromDomain(InboundTO order) {
        return PurchaseDTOConverter.INSTANCE.toDTO(order);
    }

    public static InboundTO fromPO(InboundTODTO dto) {
        return PurchaseDTOConverter.INSTANCE.toDTO(dto);
    }
}
