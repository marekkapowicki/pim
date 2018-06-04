package com.marekk.pim.product.domain.command;

import com.marekk.pim.infrastructure.Preconditions;
import com.marekk.pim.infrastructure.exception.Exceptions;
import com.marekk.pim.product.dto.ProductDTO;

class ProductFactory {
    ProductEntity create(ProductDTO dto) {
        Preconditions.checkArgument(dto != null, Exceptions.illegalState("product is null"));
        return ProductEntity.builder()
                .externalId(dto.getExternalId())
                .name(dto.getName())
                .description(dto.getDescription())
                .minOrderQuantity(dto.getMinOrderQuantity())
                .unitOfMeasure(dto.getUnitOfMeasure())
                .externalCategoryId(dto.getExternalCategoryId())
                .purchasePrice(dto.getPurchasePrice())
                .availableQuantity(dto.getAvailableQuantity())
                .build();

    }
}
