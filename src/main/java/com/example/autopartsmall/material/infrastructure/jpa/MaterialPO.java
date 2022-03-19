package com.example.autopartsmall.material.infrastructure.jpa;

import com.example.autopartsmall.material.domain.Material;
import com.example.autopartsmall.material.infrastructure.convertor.MaterialPOConverter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "material")
public class MaterialPO {
    @Id
    String id;

    String supplierId;

    // 简化，不考虑货币
    private BigDecimal price;

    public static MaterialPO fromDomain(Material material) {
        return MaterialPOConverter.INSTANCE.toPO(material);
    }

    public Material toDomain() {
        return MaterialPOConverter.INSTANCE.toDomain(this);
    }
}
