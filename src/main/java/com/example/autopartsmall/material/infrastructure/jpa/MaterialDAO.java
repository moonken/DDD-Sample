package com.example.autopartsmall.material.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialDAO extends JpaRepository<MaterialPO, String>, JpaSpecificationExecutor<MaterialPO> {

}
