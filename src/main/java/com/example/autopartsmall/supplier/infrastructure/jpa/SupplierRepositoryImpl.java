package com.example.autopartsmall.supplier.infrastructure.jpa;

import com.example.autopartsmall.supplier.domain.Supplier;
import com.example.autopartsmall.supplier.domain.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SupplierRepositoryImpl implements SupplierRepository {
    private SupplierDAO materialDAO;

    @Override
    public Supplier save(Supplier material) {
        return materialDAO.save(SupplierPO.fromDomain(material)).toDomain();
    }
}
