package com.marekk.pim.product;

import com.marekk.pim.infrastructure.api.CreateResourceRequest;
import com.marekk.pim.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(makeFinal = true, level = PRIVATE)
@AllArgsConstructor
@ToString
public class CreateProductRequest implements CreateResourceRequest {
    String externalId;
    @NonNull String name;
    String description;
    int minOrderQuantity;
    int unitOfMeasure;
    String externalCategoryId;
    BigDecimal purchasePrice;
    int availableQuantity;

    ProductDTO toDto() {
        return ProductDTO.builder()
                .externalId(externalId)
                .name(name)
                .description(description)
                .minOrderQuantity(minOrderQuantity)
                .unitOfMeasure(unitOfMeasure)
                .externalCategoryId(externalCategoryId)
                .purchasePrice(purchasePrice)
                .availableQuantity(availableQuantity).build();
    }
}
