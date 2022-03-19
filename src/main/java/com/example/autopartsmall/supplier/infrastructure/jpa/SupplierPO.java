package com.example.autopartsmall.supplier.infrastructure.jpa;

import com.example.autopartsmall.supplier.domain.Supplier;
import com.example.autopartsmall.supplier.infrastructure.convertor.SupplierPOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "supplier")
public class SupplierPO {
    @Id
    String id;

    String name;

    public static SupplierPO fromDomain(Supplier supplier) {
        return SupplierPOConverter.INSTANCE.toPO(supplier);
    }

    public Supplier toDomain() {
        return SupplierPOConverter.INSTANCE.toDomain(this);
    }
}
