package com.marekk.pim.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultProductProjection implements ProductProjection {
    String productId;
    String externalId;
    String name;
    String description;
    Integer minOrderQuantity;
    Integer unitOfMeasure;
    String categoryName;
    BigDecimal purchasePrice;
    Integer availableQuantity;
}
