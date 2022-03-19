package com.example.autopartsmall.sales.ui.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class ModifyOrderLineQuantityRequest {
    @Min(1)
    int quantity;
}
