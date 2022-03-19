package com.example.autopartsmall.purchase.application.dto;

import com.example.autopartsmall.purchase.application.convertor.PurchaseDTOConverter;
import com.example.autopartsmall.purchase.domain.InboundTOLine;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class InboundTOLineDTO {

    Long purchaseOrderLineId;

    String materialId;

    @Min(1)
    int quantity;

    public InboundTOLine toOrderLine() {
        return PurchaseDTOConverter.INSTANCE.toDomain(this);
    }
}
