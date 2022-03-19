package com.example.autopartsmall.purchase.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InboundTODAO extends JpaRepository<InboundTOPO, Long>, JpaSpecificationExecutor<InboundTOPO> {

}
