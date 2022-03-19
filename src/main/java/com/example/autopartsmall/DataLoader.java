package com.example.autopartsmall;

import com.example.autopartsmall.agency.domain.Agency;
import com.example.autopartsmall.agency.domain.AgencyId;
import com.example.autopartsmall.agency.domain.AgencyRepository;
import com.example.autopartsmall.material.domain.Material;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.material.domain.MaterialRepository;
import com.example.autopartsmall.supplier.domain.Supplier;
import com.example.autopartsmall.supplier.domain.SupplierId;
import com.example.autopartsmall.supplier.domain.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    private MaterialRepository materialRepository;
    private AgencyRepository agencyRepository;
    private SupplierRepository supplierRepository;

    @Override
    public void run(ApplicationArguments args) {
        supplierRepository.save(new Supplier(new SupplierId("S1"), "Supplier 1"));
        supplierRepository.save(new Supplier(new SupplierId("S2"), "Supplier 2"));

        materialRepository.save(new Material(new MaterialId("S1-01"), new SupplierId("S1"), new BigDecimal(200)));
        materialRepository.save(new Material(new MaterialId("S1-02"), new SupplierId("S1"), new BigDecimal(200)));
        materialRepository.save(new Material(new MaterialId("S2-01"), new SupplierId("S2"), new BigDecimal(200)));

        agencyRepository.save(new Agency(new AgencyId("A1"), "Agency 1"));
        agencyRepository.save(new Agency(new AgencyId("A2"), "Agency 2"));
    }
}
