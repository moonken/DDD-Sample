package com.example.autopartsmall.material.infrastructure.jpa;

import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.material.domain.Material;
import com.example.autopartsmall.material.domain.MaterialId;
import com.example.autopartsmall.material.domain.MaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MaterialRepositoryImpl implements MaterialRepository {
    private MaterialDAO materialDAO;

    @Override
    public Material save(Material material) {
        return materialDAO.save(MaterialPO.fromDomain(material)).toDomain();
    }

    @Override
    public Material get(MaterialId id) {
        return getPO(id).toDomain();
    }

    @Override
    public List<Material> get(List<MaterialId> ids) {
        List<String> nativeIds = ids.stream()
                .map(MaterialId::getValue)
                .collect(Collectors.toList());
        return materialDAO.findAllById(nativeIds).stream()
                .map(MaterialPO::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exist(MaterialId id) {
        return materialDAO.existsById(id.getValue());
    }

    private MaterialPO getPO(MaterialId id) {
        return materialDAO.findById(id.getValue()).orElseThrow(() -> new DomainEntityNotFoundException(id));
    }
}
