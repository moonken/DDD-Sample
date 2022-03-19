package com.example.autopartsmall.material.domain;

import com.example.autopartsmall.common.ddd.DomainService;
import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import lombok.AllArgsConstructor;

@DomainService
@AllArgsConstructor
public class MaterialService {
    private MaterialRepository repository;

    public void validateExist(MaterialId id) {
        if (!repository.exist(id)) {
            throw new DomainEntityNotFoundException(id);
        }
    }
}
