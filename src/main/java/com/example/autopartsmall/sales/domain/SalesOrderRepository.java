package com.example.autopartsmall.sales.domain;

public interface SalesOrderRepository{
    SalesOrder get(SalesOrderId id);
    SalesOrder save(SalesOrder salesOrder);
}
