package com.example.autopartsmall.sales.application;

import com.example.autopartsmall.common.ddd.ApplicationService;
import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.sales.domain.SalesOrderId;
import com.example.autopartsmall.sales.infrastructure.jpa.SalesOrderDAO;
import com.example.autopartsmall.sales.infrastructure.jpa.SalesOrderPO;
import lombok.AllArgsConstructor;

@ApplicationService
@AllArgsConstructor
public class SalesOrderReadService {
    private SalesOrderDAO dao;

    public SalesOrderPO get(Long id) {
        return dao.findById(id).orElseThrow(() -> new DomainEntityNotFoundException(new SalesOrderId(id)));
    }
}
