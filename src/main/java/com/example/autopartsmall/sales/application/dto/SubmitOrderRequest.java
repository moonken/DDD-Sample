package com.example.autopartsmall.sales.application.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SubmitOrderRequest {

    @NotNull
    String agencyId;

    @NotEmpty
    @Valid
    List<SalesOrderLineDTO> orderLines;
}
