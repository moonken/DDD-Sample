package com.example.autopartsmall.supplier.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SupplierDAO extends JpaRepository<SupplierPO, String>, JpaSpecificationExecutor<SupplierPO> {

}
