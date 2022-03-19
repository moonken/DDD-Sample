package com.example.autopartsmall.sales.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesOrderLineDAO extends JpaRepository<SalesOrderLinePO, Long>, JpaSpecificationExecutor<SalesOrderLinePO> {

}
