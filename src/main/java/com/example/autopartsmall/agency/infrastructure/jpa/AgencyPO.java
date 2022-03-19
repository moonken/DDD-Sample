package com.example.autopartsmall.agency.infrastructure.jpa;

import com.example.autopartsmall.agency.domain.Agency;
import com.example.autopartsmall.agency.infrastructure.convertor.AgencyPOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "agency")
public class AgencyPO {
    @Id
    String id;

    String name;

    public static AgencyPO fromDomain(Agency agency) {
        return AgencyPOConverter.INSTANCE.toPO(agency);
    }

    public Agency toDomain() {
        return AgencyPOConverter.INSTANCE.toDomain(this);
    }
}
