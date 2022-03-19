package com.example.autopartsmall.material.domain;

import java.util.List;

public interface MaterialRepository {
    Material save(Material material);
    Material get(MaterialId id);
    List<Material> get(List<MaterialId> ids);
    boolean exist(MaterialId id);
}
