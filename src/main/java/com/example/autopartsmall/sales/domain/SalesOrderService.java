package com.example.autopartsmall.sales.domain;

import com.example.autopartsmall.common.ddd.DomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DomainService
@AllArgsConstructor
@Slf4j
public class SalesOrderService {
    private SalesOrderRepository repository;
}
