package com.example.autopartsmall.purchase.application.convertor;

import com.example.autopartsmall.purchase.application.dto.InboundTODTO;
import com.example.autopartsmall.purchase.application.dto.InboundTOLineDTO;
import com.example.autopartsmall.purchase.domain.InboundTO;
import com.example.autopartsmall.purchase.domain.InboundTOLine;
import com.example.autopartsmall.purchase.infrastructure.convertor.PurchaseIdConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseDTOConverter extends PurchaseIdConvertor {

    PurchaseDTOConverter INSTANCE = Mappers.getMapper(PurchaseDTOConverter.class);

    InboundTOLine toDomain(InboundTOLineDTO inboundTOOrderLineDTO);

    InboundTODTO toDTO(InboundTO order);

    InboundTO toDTO(InboundTODTO dto);
}
