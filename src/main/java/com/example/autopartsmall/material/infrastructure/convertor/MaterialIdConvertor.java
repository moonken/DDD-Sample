package com.example.autopartsmall.material.infrastructure.convertor;

import com.example.autopartsmall.material.domain.MaterialId;

public interface MaterialIdConvertor {
    default MaterialId toMaterialId(String id) {
        return new MaterialId(id);
    }

    default String toPrimitive(MaterialId id) {
        return id.getValue();
    }
}
