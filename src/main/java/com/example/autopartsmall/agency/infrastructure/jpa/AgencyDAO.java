package com.example.autopartsmall.agency.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AgencyDAO extends JpaRepository<AgencyPO, String>, JpaSpecificationExecutor<AgencyPO> {

}
