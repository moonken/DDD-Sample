package com.example.autopartsmall.purchase.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitInboundTORequest {

    @NotNull
    Long purchaseOrderId;

    @NotEmpty
    @Valid
    List<InboundTOLineDTO> orderLines;
}
