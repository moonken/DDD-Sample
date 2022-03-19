package com.example.autopartsmall.common.ddd.exception;

import com.example.autopartsmall.common.ddd.EntityId;

public class DomainEntityNotFoundException extends DomainException {
    public DomainEntityNotFoundException(EntityId id) {
        super(getEntityType(id) + " with id " + id.getValue() + " not found");
    }

    private static String getEntityType(EntityId id) {
        String idTypeName = id.getClass().getSimpleName();
        return  idTypeName.substring(0, idTypeName.length() - 2);
    }
}
