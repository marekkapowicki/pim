package com.marekk.pim.product.domain.command;

import com.marekk.pim.infrastructure.BaseEntity;
import com.marekk.pim.product.dto.ProductDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
class ProductEntity extends BaseEntity {

    String externalId;
    @NonNull
    @Getter
    String name;
    String description;
    int minOrderQuantity;
    int unitOfMeasure;
    String externalCategoryId;
    BigDecimal availableQuantity;

    ProductEntity merge(ProductDTO dto) {
        externalId = dto.getExternalId();
        name = dto.getName();
        description = dto.getDescription();
        minOrderQuantity = dto.getMinOrderQuantity();
        unitOfMeasure = dto.getUnitOfMeasure();
        externalCategoryId = dto.getExternalCategoryId();
        availableQuantity = dto.getAvailableQuantity();
        return this;
    }
}
