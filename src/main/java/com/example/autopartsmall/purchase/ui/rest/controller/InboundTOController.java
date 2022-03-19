package com.example.autopartsmall.purchase.ui.rest.controller;

import com.example.autopartsmall.purchase.application.InboundTOWriteService;
import com.example.autopartsmall.purchase.application.dto.InboundTODTO;
import com.example.autopartsmall.purchase.application.dto.SubmitInboundTORequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("inbound-tos")
@AllArgsConstructor
public class InboundTOController {

    private InboundTOWriteService writeService;

    @PostMapping
    public InboundTODTO create(@Valid @RequestBody SubmitInboundTORequest request) {
        return InboundTODTO.fromDomain(writeService.submit(request));
    }
}
